package com.k_beta.android.app.mvp.delegate;

import android.os.Bundle;
import com.k_beta.android.app.mvp.base.MvpPresenter;
import com.k_beta.android.app.mvp.base.MvpView;

/**
 * Created by Kevin Dong on 2017/7/20.
 */
public interface ActivityMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    /**
     * This method must be called from {@link Activity#onCreate(Bundle)}.
     * This method internally creates the presenter and attaches the view to it.
     */
    void onCreate(Bundle bundle);

    /**
     * This method must be called from {@link Activity#onDestroy()}}.
     * This method internally detaches the view from presenter
     */
    void onDestroy();

    /**
     * This method must be called from {@link Activity#onPause()}
     */
    void onPause();

    /**
     * This method must be called from {@link Activity#onResume()}
     */
    void onResume();

    /**
     * This method must be called from {@link Activity#onStart()}
     */
    void onStart();

    /**
     * This method must be called from {@link Activity#onStop()}
     */
    void onStop();

    /**
     * This method must be called from {@link Activity#onRestart()}
     */
    void onRestart();

    /**
     * This method must be called from {@link Activity#onContentChanged()}
     */
    void onContentChanged();

    /**
     * This method must be called from {@link Activity#onSaveInstanceState(Bundle)}
     */
    void onSaveInstanceState(Bundle outState);

    /**
     * This method must be called from {@link Activity#onPostCreate(Bundle)}
     */
    void onPostCreate(Bundle savedInstanceState);

}