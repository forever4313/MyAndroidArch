package com.k_beta.android.app.presenter;

import com.k_beta.android.app.MyAndroidApplication;
import com.k_beta.android.app.contract.UserDetailContract;
import com.k_beta.android.app.domain.User;
import com.k_beta.android.app.domain.exception.DefaultErrorBundle;
import com.k_beta.android.app.domain.interactor.DefaultObserver;
import com.k_beta.android.app.domain.interactor.GetUserDetails;
import com.k_beta.android.app.mvp.MvpBasePresenter;

import javax.inject.Inject;

/**
 * Created by Kevin Dong on 2017/7/25.
 */
public class UserDetailPresenter extends MvpBasePresenter<UserDetailContract.UserDetailView> implements UserDetailContract.UserDetailPresenter{



    @Inject
    GetUserDetails getUserDetailsUseCase;

    @Override
    public void viewInited() {
        super.viewInited();
        MyAndroidApplication.getUserComponent().inject(this);
    }

    @Override
    public void loadUserDetail(int userId) {
        if(isViewAttached()){
            getView().showLoading();
        }
        getUserDetailsUseCase.execute(new UserDetailsObserver(), GetUserDetails.Params.forUser(userId));

    }

    private final class UserDetailsObserver extends DefaultObserver<User> {

        @Override public void onComplete() {
            if(isViewAttached()){
                getView().hideLoading();
            }
        }

        @Override public void onError(Throwable e) {
        }

        @Override public void onNext(User user) {
            if(isViewAttached()){
                getView().showUser(user);
            }
        }
    }
}
