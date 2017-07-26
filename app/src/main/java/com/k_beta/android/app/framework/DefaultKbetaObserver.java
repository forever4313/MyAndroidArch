package com.k_beta.android.app.framework;

import com.k_beta.android.app.domain.interactor.DefaultObserver;

/**
 * Created by Kevin Dong on 2017/7/19.
 */
public class DefaultKbetaObserver<T> extends DefaultObserver<T>{

    @Override
    public void onComplete() {
        Logger.log(Logger.SCOPE.SUBSCRIBER,"complete #### ");
    }

    @Override
    public void onError(Throwable e) {
        Logger.log(Logger.SCOPE.SUBSCRIBER, "error info  ### " + e.getMessage());

    }

    @Override
    public void onNext(T t) {
        Logger.log(Logger.SCOPE.SUBSCRIBER, "next ### ");

    }

}
