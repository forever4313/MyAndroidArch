package com.k_beta.android.app.contract;

import com.k_beta.android.app.domain.User;
import com.k_beta.android.app.mvp.base.MvpPresenter;
import com.k_beta.android.app.mvp.base.MvpView;

/**
 * Created by Kevin Dong on 2017/7/25.
 */
public interface UserDetailContract {

    interface UserDetailView extends MvpView{
        void showUser(User user);
        void hideLoading();
        void showLoading();
    }


    interface UserDetailPresenter extends MvpPresenter<UserDetailContract.UserDetailView> {
        void loadUserDetail(int userId);

    }
}
