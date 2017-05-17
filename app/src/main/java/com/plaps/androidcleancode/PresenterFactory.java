package com.plaps.androidcleancode;

import com.plaps.androidcleancode.home.HomePresenter;

/**
 * Created by sebastian.muller on 17/5/2017.
 */

public interface PresenterFactory<T extends  Presenter> {
    T create();
}
