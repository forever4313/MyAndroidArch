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
package com.k_beta.android.app;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;
import com.k_beta.android.app.data.MyResponse;
import com.k_beta.android.app.data.MyResponseDeserializer;
import com.k_beta.android.app.data.net.RestApi;
import com.k_beta.android.app.data.util.GsonUtil;
import com.k_beta.android.app.internal.di.components.*;
import com.k_beta.android.app.internal.di.modules.*;
import com.squareup.leakcanary.LeakCanary;

/**
 * Android Main Application
 */
public class MyAndroidApplication extends Application {

    private static ApplicationComponent applicationComponent;
    private static UserComponent userComponent;
    private static MyAndroidApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance  = this;
        this.initializeInjector();
        this.initializeLeakDetection();
        this.initialSubInjector();
        // 注册解析方法
        GsonUtil.registerTypeAdapter(MyResponse.class, new MyResponseDeserializer());
        if (BuildConfig.DEBUG) {    // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .repositoryModule(new RepositoryModule())
                .networkModule(new NetworkModule(RestApi.API_BASE_URL))
                .build();
    }

    private void initialSubInjector() {
        userComponent = DaggerUserComponent.builder()
                .applicationComponent(applicationComponent)
                .userModule(new UserModule())
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }


    public static UserComponent getUserComponent() {
        return userComponent;
    }

    public static MyAndroidApplication getInstance() {
        return instance;
    }

}
