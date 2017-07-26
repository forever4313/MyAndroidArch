package com.k_beta.android.app.mvp.base;

import android.support.annotation.UiThread;

/**
 * Created by Kevin Dong on 2017/7/20.
 */
public interface MvpPresenter<V extends MvpView> {


    /**
     * call back when view is attached
     *
     */
    void viewInited();
    /**
     * Set or attach the view to this presenter
     */
    @UiThread
    void attachView(V view);

    /**
     * Will be called if the view has been destroyed. Typically this method will be invoked from
     * <code>Activity.detachView()</code> or <code>Fragment.onDestroyView()</code>
     */
    @UiThread
    void detachView(boolean retainInstance);
}