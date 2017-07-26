package com.k_beta.android.app.contract;

import com.k_beta.android.app.domain.User;
import com.k_beta.android.app.mvp.base.MvpPresenter;

import java.util.List;

/**
 * Created by Kevin Dong on 2017/7/24.
 */
public interface UserListContract {

    interface UserListView extends MvpLoadDataView{
        void addDatas(List<User> users);
        void showDatas(List<User> users);

    }


    interface UserListPresenter extends MvpPresenter<UserListView> {
        void onUserClicked(User user);
        void showLoading();
        void hideLoading();
        void showViewRetry();
        void hideViewRetry();
        void showErrorMessage(String msg);
        void showUsersCollectionInView(List<User> list);
        void loadUserList();
    }

}
