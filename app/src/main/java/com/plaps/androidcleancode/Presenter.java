package com.plaps.androidcleancode;

/**
 * Created by sebastian.muller on 17/5/2017.
 */

public interface Presenter<V>{
    void onViewAttached(V view);
    void onViewDetached();
    void onDestroyed();
}