/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.k_beta.android.app.presenter;

import com.k_beta.android.app.MyAndroidApplication;
import com.k_beta.android.app.contract.UserListContract;
import com.k_beta.android.app.domain.User;
import com.k_beta.android.app.domain.interactor.DefaultObserver;
import com.k_beta.android.app.domain.interactor.GetUserList;
import com.k_beta.android.app.internal.di.scope.PerActivity;
import com.k_beta.android.app.mvp.MvpBasePresenter;

import javax.inject.Inject;
import java.util.List;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class UserListPresenter extends MvpBasePresenter<UserListContract.UserListView> implements UserListContract.UserListPresenter {

    @Inject
    GetUserList getUserListUseCase;

    /**
     * Initializes the presenter by start retrieving the user list.
     */
    public void initialize() {
        this.loadUserList();
    }

    @Override
    public void viewInited() {
        MyAndroidApplication.getUserComponent().inject(this);
        /**
         * 加载数据
         */
        loadUserList();
    }

    /**
     * Loads all users.
     */
    @Override
    public void loadUserList() {
        this.hideViewRetry();
        this.showLoading();
        this.getUserList();
    }

    public void onUserClicked(User userModel) {
//        this.getView().viewUser(userModel);
    }

    @Override
    public void showLoading() {
        if(isViewAttached()){
            this.getView().showLoading();
        }

    }

    @Override
    public void hideLoading() {
        if(isViewAttached()){
            this.getView().hideLoading();
        }

    }

    @Override
    public void showViewRetry() {
        if(isViewAttached()) {
            this.getView().showRetry();
        }
    }

    @Override
    public void hideViewRetry() {
        if(isViewAttached()) {
            this.getView().hideRetry();
        }
    }

    @Override
    public void showErrorMessage(String msg) {
        if(isViewAttached()) {
//            String errorMessage = ErrorMessageFactory.create(,
//                    errorBundle.getException());
            String errorMessage = "xxxx";
            this.getView().showError(errorMessage);
        }
    }

    @Override
    public void showUsersCollectionInView(List<User> list) {
        if(isViewAttached()) {
            this.getView().showDatas(list);
        }
    }


    public void getUserList() {
        this.getUserListUseCase.execute(new UserListObserver(), null);
    }


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (getUserListUseCase != null) {
            this.getUserListUseCase.dispose();
        }

    }

    private final class UserListObserver extends DefaultObserver<List<User>> {

        @Override
        public void onComplete() {
            UserListPresenter.this.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            UserListPresenter.this.hideLoading();
            UserListPresenter.this.showErrorMessage("xxxx");
            UserListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<User> users) {

            UserListPresenter.this.showUsersCollectionInView(users);
        }
    }
}
