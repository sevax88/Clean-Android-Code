package com.plaps.androidcleancode;

import android.view.View;

import com.plaps.androidcleancode.home.HomeView;

/**
 * Created by sebastian.muller on 17/5/2017.
 */

public interface Presenter<V extends HomeView>{
    void onViewAttached(V view);
    void onViewDetached();
    void onDestroyed();
}