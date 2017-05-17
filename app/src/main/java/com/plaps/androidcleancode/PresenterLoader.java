package com.plaps.androidcleancode;

import android.content.Context;
import android.support.v4.content.Loader;


/**
 * Created by sebastian.muller on 17/5/2017.
 */

public class PresenterLoader<T extends  Presenter> extends Loader {

    private PresenterFactory factory;
    private T presenter;

    public PresenterLoader(Context context) {
        super(context);
    }

    public PresenterLoader(Context context, PresenterFactory factory) {
        super(context);
        this.factory = factory;
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
        // Deliver the result
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
//        presenter.onDestroyed();
        presenter = null;
    }

}
