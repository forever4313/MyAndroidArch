package com.k_beta.android.app.internal.di.modules;


import com.k_beta.android.app.data.net.RestApi;
import com.k_beta.android.app.internal.di.scope.ApiScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import javax.inject.Singleton;

/**
 * Created by Denis Kholevinsky
 */


@Module
public class ApiModule {

    @Provides
    @Singleton
    RestApi provideCommonApiService(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

}
