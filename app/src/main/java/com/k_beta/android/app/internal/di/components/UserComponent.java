package com.k_beta.android.app.internal.di.components;

import com.k_beta.android.app.internal.di.modules.UserModule;
import com.k_beta.android.app.internal.di.scope.PerActivity;
import com.k_beta.android.app.presenter.UserDetailPresenter;
import com.k_beta.android.app.presenter.UserListPresenter;
import com.k_beta.android.app.view.fragment.UserListFragment;
import dagger.Component;

/**
 * Created by Kevin Dong on 2017/7/24.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {UserModule.class})
public interface UserComponent {

    void inject(UserListFragment userListFragment);
    void inject(UserListPresenter userListPresenter);
    void inject(UserDetailPresenter presenter);
}