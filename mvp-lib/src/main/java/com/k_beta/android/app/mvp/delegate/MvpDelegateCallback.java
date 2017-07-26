package com.k_beta.android.app.mvp.delegate;

import android.support.annotation.NonNull;
import com.k_beta.android.app.mvp.base.MvpPresenter;
import com.k_beta.android.app.mvp.base.MvpView;

/**
 * Created by Kevin Dong on 2017/7/20.
 */
public interface MvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> {

    /**
     * Creates the presenter instance
     *
     * @return the created presenter instance
     */
    @NonNull
    P createPresenter();

    /**
     * Get the presenter. If null is returned, then a internally a new presenter instance gets
     * created
     * by calling {@link #createPresenter()}
     *
     * @return the presenter instance. can be null.
     */
    P getPresenter();

    /**
     * Sets the presenter instance
     *
     * @param presenter The presenter instance
     */
    void setPresenter(P presenter);

    /**
     * Get the MvpView for the presenter
     *
     * @return The view associated with the presenter
     */
    V getMvpView();
}
