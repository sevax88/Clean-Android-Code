package com.plaps.androidcleancode.home;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.plaps.androidcleancode.BaseApp;
import com.plaps.androidcleancode.Presenter;
import com.plaps.androidcleancode.PresenterFactory;
import com.plaps.androidcleancode.PresenterLoader;
import com.plaps.androidcleancode.R;
import com.plaps.androidcleancode.models.CityListData;
import com.plaps.androidcleancode.models.CityListResponse;
import com.plaps.androidcleancode.networking.Service;

import javax.inject.Inject;

public class HomeActivity extends BaseApp implements HomeView, LoaderManager.LoaderCallbacks<Presenter> {

    private RecyclerView list;
    @Inject
    public  Service service;
    private ProgressBar progressBar;
    private static final int LOADER_ID = 101;
    private Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

        getDeps().inject(this);

        renderView();
        init();

//        HomePresenter presenter = new HomePresenter(service, this);
        ((HomePresenter)presenter).getCityList();
    }

    public  void renderView(){
        setContentView(R.layout.activity_home);
        list = (RecyclerView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getityListSuccess(CityListResponse cityListResponse) {

        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), cityListResponse.getData(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CityListData Item) {
                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        list.setAdapter(adapter);

    }


    @Override
    public Loader<Presenter> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, new Hom());
    }

    @Override
    public void onLoadFinished(Loader<Presenter> loader, Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onLoaderReset(Loader loader) {
        presenter = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewAttached(this);
    }

    @Override
    protected void onStop() {
        presenter.onViewDetached();
        super.onStop();
    }

}
