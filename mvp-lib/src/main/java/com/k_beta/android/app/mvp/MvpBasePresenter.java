package com.k_beta.android.app.mvp;

import android.support.annotation.UiThread;
import com.k_beta.android.app.mvp.base.MvpPresenter;
import com.k_beta.android.app.mvp.base.MvpView;

import java.lang.ref.WeakReference;

/**
 * Created by Kevin Dong on 2017/7/20.
 */
public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> viewRef;

    @Override
    public void viewInited() {

    }

    @UiThread
    @Override public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
    }

    /**
     * Get the attached view. You should always call {@link #isViewAttached()} to check if the view
     * is
     * attached to avoid NullPointerExceptions.
     *
     * @return <code>null</code>, if view is not attached, otherwise the concrete view instance
     */
    @UiThread public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    @UiThread
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @UiThread
    @Override public void detachView(boolean retainInstance) {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}