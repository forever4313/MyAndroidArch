package com.k_beta.android.app.framework.view;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.k_beta.android.app.MyAndroidApplication;
import com.k_beta.android.app.R;
import com.k_beta.android.app.internal.di.components.ApplicationComponent;
import com.k_beta.android.app.mvp.MvpActivity;
import com.k_beta.android.app.mvp.base.MvpPresenter;
import com.k_beta.android.app.mvp.base.MvpView;
import com.k_beta.android.app.navigation.Navigator;

import javax.inject.Inject;

/**
 *
 * 此类做一些基本的例如 统计分析或者埋点之类的操作
 * Created by Kevin Dong on 2017/7/20.
 */

public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V,P> {


    @Inject
    Navigator navigator;
    LinearLayout containerLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((MyAndroidApplication) getApplication()).getApplicationComponent();
    }



    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }
    @Override
    public void setContentView(View view) {
        super.setContentView(R.layout.activity_base_layout);
        containerLayout = (LinearLayout) findViewById(R.id.base_root_layout);
        if (containerLayout == null) return;
        containerLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    protected  void hideToolbar(){
        if(toolbar !=null ) {
            toolbar.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
