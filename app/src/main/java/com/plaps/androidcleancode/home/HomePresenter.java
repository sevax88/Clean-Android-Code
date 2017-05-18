package com.plaps.androidcleancode.home;

import com.plaps.androidcleancode.Presenter;
import com.plaps.androidcleancode.deps.DaggerDeps;
import com.plaps.androidcleancode.deps.Deps;
import com.plaps.androidcleancode.models.CityListResponse;
import com.plaps.androidcleancode.networking.NetworkError;
import com.plaps.androidcleancode.networking.NetworkModule;
import com.plaps.androidcleancode.networking.Service;

import java.io.File;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ennur on 6/25/16.
 */
public class HomePresenter implements Presenter {

    @Inject
    Service service;
    Deps deps;
    private HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter() {
        deps = DaggerDeps.builder().networkModule(new NetworkModule()).build();
        deps.inject(this);
        subscriptions = new CompositeSubscription();
    }

    public void getCityList() {
        view.showWait();

        Subscription subscription = service.getCityList(new Service.GetCityListCallback() {
            @Override
            public void onSuccess(CityListResponse cityListResponse) {
                view.removeWait();
                view.getCityListSuccess(cityListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }

    @Override
    public void onViewAttached(HomeView view) {
        this.view =  view;
    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void onDestroyed() {

    }
}
