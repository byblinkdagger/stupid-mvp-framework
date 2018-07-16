package com.oragee.groups.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.oragee.groups.R;
import com.oragee.groups.base.BaseActivity;
import com.oragee.groups.base.BaseFragment;
import com.oragee.groups.event.LoginEvent;
import com.oragee.groups.ui.home.HomeFragment;
import com.oragee.groups.ui.mine.MineFragment;
import com.oragee.groups.ui.order.OrderFragment;
import com.oragee.groups.util.PreferencesUtils;
import com.oragee.groups.util.SnackBarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainP> implements MainContract.V {

    @BindView(R.id.fl_main_content)
    FrameLayout flMainContent;
    @BindView(R.id.tab_home)
    ImageView tabHome;
    @BindView(R.id.tab_submit)
    ImageView tabSubmit;
    @BindView(R.id.tab_order)
    ImageView tabOrder;
    @BindView(R.id.tab_mine)
    ImageView tabMine;

    ImageView[] tabImgs;
    BaseFragment[] contentFragment;
    int[] imgRes = new int[]{R.drawable.home_selected, R.drawable.home_selected, R.drawable.home_selected};
    int[] imgSelectRes = new int[]{R.drawable.home_selected, R.drawable.home_selected, R.drawable.home_selected};

    //退出确认
    private long lastTime = 0;
    private final int EXITGAP = 2000;
    FragmentManager supportFragmentManager;
    //用于记录Fragment的tag
    private static final String SAVED_INDEX = "FRAGMENT_TAG";
    private int mIndex = 0;

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        supportFragmentManager = getSupportFragmentManager();

        HomeFragment homeFragment = (HomeFragment) supportFragmentManager.findFragmentByTag("0");
        OrderFragment orderFragment = (OrderFragment) supportFragmentManager.findFragmentByTag("1");
        MineFragment mineFragment = (MineFragment) supportFragmentManager.findFragmentByTag("2");

        if (null == homeFragment) {
            homeFragment = new HomeFragment();
        }

        if (null == orderFragment) {
            orderFragment = new OrderFragment();
        }

        if (null == mineFragment) {
            mineFragment = new MineFragment();
        }

        if (null != savedInstanceState) {
            mIndex = savedInstanceState.getInt(SAVED_INDEX, 0);
            tabImgs = new ImageView[]{tabHome, tabOrder, tabMine};
            contentFragment = new BaseFragment[]{homeFragment, orderFragment, mineFragment};
            setCurrentTab(mIndex);
        } else {
            tabImgs = new ImageView[]{tabHome, tabOrder, tabMine};
            contentFragment = new BaseFragment[]{homeFragment, orderFragment, mineFragment};
//            supportFragmentManager.beginTransaction().add(R.id.fl_main_content, contentFragment[0]).commit();
            setCurrentTab(0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogout(LoginEvent.OnLogoutEvent onLogoutEvent) {
        PreferencesUtils.removeSharedPreferences(this, "token");
        PreferencesUtils.removeSharedPreferences(this, "login");
//        openLogin();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SAVED_INDEX, mIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setP() {
        mP = new MainP(this);
    }

    private void setCurrentTab(int index) {
        mIndex = index;
        for (int i = 0; i < tabImgs.length; i++) {
            if (i == index) {
                tabImgs[i].setImageResource(imgSelectRes[i]);
                if (contentFragment[i].isAdded()) {
                    supportFragmentManager.beginTransaction().show(contentFragment[i]).commit();
                } else {
                    supportFragmentManager.beginTransaction().add(R.id.fl_main_content, contentFragment[i], String.valueOf(index)).commit();
                }
            } else {
                tabImgs[i].setImageResource(imgRes[i]);
                if (contentFragment[i].isAdded()) {
                    supportFragmentManager.beginTransaction().hide(contentFragment[i]).commit();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - lastTime) > EXITGAP) {
            SnackBarUtils.makeShort(getWindow().getDecorView(), getString(R.string.exit_msg)).show();
        } else {
            finish();
        }
        lastTime = System.currentTimeMillis();
    }

    @OnClick({R.id.tab_home, R.id.tab_submit, R.id.tab_order, R.id.tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                setCurrentTab(0);
                break;
            case R.id.tab_submit:
                break;
            case R.id.tab_order:
                setCurrentTab(1);
                break;
            case R.id.tab_mine:
                setCurrentTab(2);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
