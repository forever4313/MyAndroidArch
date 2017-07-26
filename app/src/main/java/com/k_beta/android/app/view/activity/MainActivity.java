package com.k_beta.android.app.view.activity;

import android.os.Bundle;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.k_beta.android.app.R;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseCommonActivity implements BottomNavigationBar.OnTabSelectedListener{

    @BindView(R.id.btn_load_data)
    Button btn_LoadData;
    @BindView(R.id.qr_scan)
    Button btn_qr_scan;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ButterKnife.bind(this);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_sort_black_24dp, "最新").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.ic_whatshot_black_24dp, "最热").setActiveColorResource(R.color.primary))
                .addItem(new BottomNavigationItem(R.drawable.ic_settings_black_24dp, "设置").setActiveColorResource(R.color.setting_color))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * Goes to the user list screen.
     */
    @OnClick(R.id.btn_load_data)
    void navigateToUserList() {
        this.navigator.navigateToUserList();
    }

    @OnClick(R.id.qr_scan)
    void navigateToQrScan(){
        this.navigator.navigateToQrScan();
    }

    @OnClick(R.id.select_img)
    void navigateToSelectImg(){
        this.navigator.navigateToSelectImg();
    }

    @Override
    public void onTabSelected(int i) {

    }

    @Override
    public void onTabUnselected(int i) {

    }

    @Override
    public void onTabReselected(int i) {

    }
}
