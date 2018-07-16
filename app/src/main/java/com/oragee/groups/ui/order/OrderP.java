package com.oragee.groups.ui.order;

/**
 * Created by lucky on 2018/7/16 0016
 * Here be dragon
 * 前方高能
 */

public class OrderP implements OrderContract.P {

    private OrderM mM;
    private OrderContract.V mView;

    public OrderP(OrderContract.V mView) {
        this.mView = mView;
        mM = new OrderM();
    }
}
