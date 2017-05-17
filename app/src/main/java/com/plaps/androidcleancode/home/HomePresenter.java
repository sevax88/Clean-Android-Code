package com.plaps.androidcleancode.home;

import android.view.View;

import com.plaps.androidcleancode.Presenter;
import com.plaps.androidcleancode.models.CityListResponse;
import com.plaps.androidcleancode.networking.NetworkError;
import com.plaps.androidcleancode.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ennur on 6/25/16.
 */
public class HomePresenter implements Presenter {
    private  Service service;
    private HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter() {
    }

    public void getCityList() {
        view.showWait();

        Subscription subscription = service.getCityList(new Service.GetCityListCallback() {
            @Override
            public void onSuccess(CityListResponse cityListResponse) {
                view.removeWait();
                view.getityListSuccess(cityListResponse);
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
