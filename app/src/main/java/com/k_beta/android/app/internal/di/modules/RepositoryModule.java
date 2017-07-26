package com.k_beta.android.app.internal.di.modules;

import com.k_beta.android.app.data.repository.UserDataRepository;
import com.k_beta.android.app.domain.repository.UserRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Kevin Dong on 2016/12/20.
 */
@Module
public class RepositoryModule {


    @Singleton
    @Provides
    UserRepository provideUserRepository(UserDataRepository userDataRepository){
        return userDataRepository;
    }
}
