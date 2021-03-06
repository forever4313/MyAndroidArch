package com.k_beta.android.app.mvp.lce;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.k_beta.android.app.mvp.MvpActivity;
import com.k_beta.android.app.mvp.R;
import com.k_beta.android.app.mvp.base.MvpPresenter;

/**
 * Created by Kevin Dong on 2017/7/20.
 */
public abstract class MvpLceActivity<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends MvpActivity<V, P> implements MvpLceView<M> {

    protected View loadingView;
    protected CV contentView;
    protected TextView errorView;

    @CallSuper
    @Override public void onContentChanged() {
        super.onContentChanged();
        loadingView = createLoadingView();
        contentView = createContentView();
        errorView = createErrorView();

        if (loadingView == null) {
            throw new NullPointerException(
                    "Loading view is null! Have you specified a loading view in your layout xml file?"
                            + " You have to give your loading View the id R.id.loadingView");
        }

        if (contentView == null) {
            throw new NullPointerException(
                    "Content view is null! Have you specified a content view in your layout xml file?"
                            + " You have to give your content View the id R.id.contentView");
        }

        if (errorView == null) {
            throw new NullPointerException(
                    "Error view is null! Have you specified an error view in your layout xml file?"
                            + " You have to give your error View the id R.id.errorView");
        }

        errorView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onErrorViewClicked();
            }
        });
    }

    /**
     * Create the loading view. Default is {@code findViewById(R.id.loadingView)}
     * @return the loading view
     */
    @NonNull protected View createLoadingView() {
        return findViewById(R.id.loadingView);
    }

    /**
     * Create the content view. Default is {@code findViewById(R.id.contentView)}
     * @return the content view
     */
    @NonNull protected CV createContentView() {
        return (CV) findViewById(R.id.contentView);
    }

    /**
     * Create the loading view. Default is {@code findViewById(R.id.errorView)}
     * @return the error view
     */
    @NonNull
    protected TextView createErrorView(){
        return (TextView) findViewById(R.id.errorView);
    }

    /**
     * Called if the error view has been clicked. To disable clicking on the errorView use
     * <code>errorView.setClickable(false)</code>
     */
    protected void onErrorViewClicked() {
        loadData(false);
    }

    @Override public void showLoading(boolean pullToRefresh) {

        if (!pullToRefresh) {
            animateLoadingViewIn();
        }

        // otherwise the pull to refresh widget will already display a loading animation
    }

    /**
     * Override this method if you want to provide your own animation for showing the loading view
     */
    protected void animateLoadingViewIn() {
        LceAnimator.showLoading(loadingView, contentView, errorView);
    }

    @Override public void showContent() {
        animateContentViewIn();
    }

    /**
     * Called to animate from loading view to content view
     */
    protected void animateContentViewIn() {
        LceAnimator.showContent(loadingView, contentView, errorView);
    }

    /**
     * Get the error message for a certain Exception that will be shown on {@link
     * #showError(Throwable, boolean)}
     */
    protected abstract String getErrorMessage(Throwable e, boolean pullToRefresh);

    /**
     * The default behaviour is to display a toast message as light error (i.e. pull-to-refresh
     * error).
     * Override this method if you want to display the light error in another way (like crouton).
     */
    protected void showLightError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override public void showError(Throwable e, boolean pullToRefresh) {

        String errorMsg = getErrorMessage(e, pullToRefresh);

        if (pullToRefresh) {
            showLightError(errorMsg);
        } else {
            errorView.setText(errorMsg);
            animateErrorViewIn();
        }
    }

    /**
     * Animates the error view in (instead of displaying content view / loading view)
     */
    protected void animateErrorViewIn() {
        LceAnimator.showErrorView(loadingView, contentView, errorView);
    }
}