package com.oragee.groups.ui.mine;

/**
 * Created by lucky on 2018/7/16 0016
 * Here be dragon
 * 前方高能
 */

public class MineP implements MineContract.P {

    private MineContract.V mView;
    private MineM mM;

    public MineP(MineContract.V mView) {
        this.mView = mView;
        mM = new MineM();
    }
}
