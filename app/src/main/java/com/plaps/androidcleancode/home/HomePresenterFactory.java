package com.plaps.androidcleancode.home;

import com.plaps.androidcleancode.Presenter;
import com.plaps.androidcleancode.PresenterFactory;

/**
 * Created by sebastian.muller on 17/5/2017.
 */

class HomePresenterFactory implements PresenterFactory {
    @Override
    public Presenter create() {
        return new HomePresenter();
    }
}
