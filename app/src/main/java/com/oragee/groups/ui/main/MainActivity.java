package com.oragee.groups.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
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
import com.oragee.groups.util.BottomNavigationViewHelper;
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
    @BindView(R.id.bottom_Nav)
    BottomNavigationView bottomNav;

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
        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_Nav);
        BottomNavigationViewHelper.disableShiftMode(bottomNav);
        bottomNav.setItemIconTintList(null);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                resetToDefaultIcon();
                int i = item.getItemId();
                if (i == R.id.item_home) {
                    item.setIcon(R.mipmap.main_home_2);
                } else if (i == R.id.item_act) {
                    item.setIcon(R.mipmap.main_act_2);
                } else if (i == R.id.item_text) {
                    item.setIcon(R.mipmap.main_text_2);
                } else if (i == R.id.item_shop) {
                    item.setIcon(R.mipmap.main_shop_2);
                } else {
                    item.setIcon(R.mipmap.main_person_2);
                }
                return true;
            }
        });
    }

    private void resetToDefaultIcon() {
        MenuItem home = bottomNav.getMenu().findItem(R.id.item_home);
        home.setIcon(R.mipmap.main_home_1);
        MenuItem act = bottomNav.getMenu().findItem(R.id.item_act);
        act.setIcon(R.mipmap.main_act_1);
        MenuItem text = bottomNav.getMenu().findItem(R.id.item_text);
        text.setIcon(R.mipmap.main_text_1);
        MenuItem shop = bottomNav.getMenu().findItem(R.id.item_shop);
        shop.setIcon(R.mipmap.main_shop_1);
        MenuItem person = bottomNav.getMenu().findItem(R.id.item_person);
        person.setIcon(R.mipmap.main_person_1);
    }

    @Override
    public void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showErrorView();
            }
        },20000);
    }

    @Override
    public void setP() {
        mP = new MainP(this);
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

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
