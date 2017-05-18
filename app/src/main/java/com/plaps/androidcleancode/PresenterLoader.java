package com.plaps.androidcleancode;

import android.content.Context;
import android.support.v4.content.Loader;


/**
 * Created by sebastian.muller on 17/5/2017.
 */

public class PresenterLoader<T extends  Presenter> extends Loader {

    private T presenter;
    private PresenterFactory<T> mfactory;

    public PresenterLoader(Context context, PresenterFactory<T> aSpecificfactory) {
        super(context);
        this.mfactory = aSpecificfactory;
    }


    @Override
    protected void onStartLoading() {
        if (presenter != null){
            deliverResult(presenter);
        }
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        // Create the Presenter using the Factory
//        presenter = factory.create();
//        presenter = new HomePresenter()
        presenter = mfactory.create();
        // Deliver the result
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
//        presenter.onDestroyed();
        presenter = null;
    }

}
