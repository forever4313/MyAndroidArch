package com.k_beta.android.app.framework.view;


import android.os.Bundle;
import android.widget.Toast;
import com.k_beta.android.app.internal.di.HasComponent;
import com.k_beta.android.app.mvp.MvpFragment;
import com.k_beta.android.app.mvp.base.MvpPresenter;
import com.k_beta.android.app.mvp.base.MvpView;
import com.k_beta.android.app.navigation.Navigator;

import javax.inject.Inject;

/**
 * 此类做一些基本的例如 统计分析或者埋点之类的操作
 * Created by Kevin Dong on 2017/7/20.
 */
public abstract  class BaseMvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpFragment<V,P> {

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
