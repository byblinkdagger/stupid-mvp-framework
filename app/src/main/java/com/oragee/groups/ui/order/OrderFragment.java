package com.oragee.groups.ui.order;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oragee.groups.R;
import com.oragee.groups.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseFragment<OrderP> implements OrderContract.V {


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void setP() {
        mP = new OrderP(this);
    }
}
