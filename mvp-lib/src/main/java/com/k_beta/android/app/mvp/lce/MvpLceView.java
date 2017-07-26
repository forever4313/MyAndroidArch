package com.k_beta.android.app.mvp.lce;

import android.support.annotation.UiThread;
import com.k_beta.android.app.mvp.base.MvpView;

/**
 * Created by Kevin Dong on 2017/7/20.
 */
public interface MvpLceView<M> extends MvpView {

    /**
     * Display a loading view while loading data in background.
     * <b>The loading view must have the id = R.id.loadingView</b>
     *
     * @param pullToRefresh true, if pull-to-refresh has been invoked loading.
     */
    @UiThread
    void showLoading(boolean pullToRefresh);

    /**
     * Show the content view.
     *
     * <b>The content view must have the id = R.id.contentView</b>
     */
    @UiThread
    void showContent();

    /**
     * Show the error view.
     * <b>The error view must be a TextView with the id = R.id.errorView</b>
     *
     * @param e The Throwable that has caused this error
     * @param pullToRefresh true, if the exception was thrown during pull-to-refresh, otherwise
     * false.
     */
    @UiThread
    void showError(Throwable e, boolean pullToRefresh);

    /**
     * The data that should be displayed with {@link #showContent()}
     */
    @UiThread
    void setData(M data);

    /**
     * Load the data. Typically invokes the presenter method to load the desired data.
     * <p>
     * <b>Should not be called from presenter</b> to prevent infinity loops. The method is declared
     * in
     * the views interface to add support for view state easily.
     * </p>
     *
     * @param pullToRefresh true, if triggered by a pull to refresh. Otherwise false.
     */
    @UiThread
    void loadData(boolean pullToRefresh);
}
