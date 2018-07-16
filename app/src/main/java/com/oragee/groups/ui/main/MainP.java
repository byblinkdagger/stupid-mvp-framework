package com.oragee.groups.ui.main;

import com.oragee.groups.base.BaseP;

/**
 * Created by lucky on 2017/11/24.
 * Here be dragons
 * 前方高能
 */

public class MainP extends BaseP implements MainContract.P {

    private MainContract.V mView;
    private MainContract.M mModel;

    public MainP(MainContract.V mView) {
        this.mView = mView;
        mModel = new MainM();
    }
}
