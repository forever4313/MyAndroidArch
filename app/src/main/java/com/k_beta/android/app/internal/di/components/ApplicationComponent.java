package com.k_beta.android.app.internal.di.components;

import android.content.Context;
import com.k_beta.android.app.MyAndroidApplication;
import com.k_beta.android.app.data.cache.UserCache;
import com.k_beta.android.app.domain.executor.PostExecutionThread;
import com.k_beta.android.app.domain.executor.ThreadExecutor;
import com.k_beta.android.app.domain.repository.UserRepository;
import com.k_beta.android.app.internal.di.modules.ApiModule;
import com.k_beta.android.app.internal.di.modules.AppModule;
import com.k_beta.android.app.internal.di.modules.NetworkModule;
import com.k_beta.android.app.internal.di.modules.RepositoryModule;
import com.k_beta.android.app.view.activity.BaseCommonActivity;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by Kevin Dong on 2016/12/20.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                ApiModule.class,
                NetworkModule.class,
                RepositoryModule.class
        }
)
public interface ApplicationComponent {

    void inject(BaseCommonActivity baseActivity);


    //Exposed to sub-graphs.
    MyAndroidApplication getApplication();
    Context context();

    UserCache getUserCache();

    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    UserRepository userRepository();



}
