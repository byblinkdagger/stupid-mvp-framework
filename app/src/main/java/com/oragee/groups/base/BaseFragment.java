package com.oragee.groups.base;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.oragee.groups.R;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by lucky on 2017/11/24.
 * Here be dragons
 * 前方高能
 */

public abstract class BaseFragment<T extends IP> extends Fragment implements LifecycleProvider<FragmentEvent>, IFV {

    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();
    protected T mP;
    protected View mRootView;
    private ViewGroup childContainer;
    private RelativeLayout rlErrorView;
    private Button btnRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_base, container, false);
        prepareView();
        return mRootView;
    }

    @Override
    @NonNull
    @CheckResult
    public final Observable<FragmentEvent> lifecycle() {
        return mLifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(mLifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(mLifecycleSubject);
    }

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        mLifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        ButterKnife.bind(this, view);
        setP();
        initViews(view);
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        mLifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        mLifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        mLifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mLifecycleSubject.onNext(FragmentEvent.DESTROY);
        if (mLoadingDialog != null) {
            mLoadingDialog = null;
        }
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        mLifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }

    protected void prepareView() {
        childContainer = mRootView.findViewById(R.id.bf_child_container);
        LayoutInflater mInflater = LayoutInflater.from(getContext());
        mInflater.inflate(getLayoutResource(), childContainer, true);
        rlErrorView = mRootView.findViewById(R.id.error_layout);
        btnRefresh = mRootView.findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidErrorView();
                initData();
            }
        });
    }

    protected abstract int getLayoutResource();

    protected abstract void initViews(View view);

    protected abstract void initData();

    private ProgressDialog mLoadingDialog;

    @Override
    public void showSimpleLoading(String msg) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(getContext());
            mLoadingDialog.setMessage(msg);
        }
        if (mLoadingDialog.isShowing()) {
            mLoadingDialog.setMessage(msg);
//            mLoadingDialog.notify();
        } else {
            mLoadingDialog.show();
        }
    }

    @Override
    public void endSimpleLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    protected void showErrorView() {
        if (rlErrorView.getVisibility() != View.VISIBLE) {
            rlErrorView.setVisibility(View.VISIBLE);
        }
    }

    private void hidErrorView() {
        if (rlErrorView.getVisibility() != View.GONE) {
            rlErrorView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onShowError() {
        showErrorView();
    }
}
