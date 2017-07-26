package com.k_beta.android.app.internal.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;
import com.k_beta.android.app.MyAndroidApplication;
import com.k_beta.android.app.UIThread;
import com.k_beta.android.app.data.cache.UserCache;
import com.k_beta.android.app.data.cache.UserCacheImpl;
import com.k_beta.android.app.data.executor.JobExecutor;
import com.k_beta.android.app.data.net.RestApi;
import com.k_beta.android.app.domain.executor.PostExecutionThread;
import com.k_beta.android.app.domain.executor.ThreadExecutor;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import javax.inject.Singleton;

/**
 * Created by Kevin Dong on 2016/12/20.
 */

@Module
public class AppModule {

    private Context appContext;
    private MyAndroidApplication application;
    public AppModule(@NonNull MyAndroidApplication application) {
        this.application = application;
        this.appContext = application;
    }

    @Singleton
    @Provides
    MyAndroidApplication provideApplication(){
        return this.application;
    }


    @Singleton
    @Provides
    Context provideContext() {
        return this.appContext;
    }


    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }


    @Provides
    @Singleton
    RestApi provideApiService(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }


    @Provides
    @Singleton
    UserCache provideUserCache(UserCacheImpl userCache){
        return userCache;
    }

}
