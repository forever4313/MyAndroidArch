package com.k_beta.android.app.framework;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author: Kevin Dong
 * @date:2016/4/9
 * @email:dongkai@nodescm.com
 */
public class NetWorkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request authorised = originalRequest.newBuilder().build();
//                .addHeader("token", LocalStoreManager.getUserToken())
//                .addHeader("phone", LocalStoreManager.getUserMobile())
//                .addHeader("version",AppUtil.getAppVersion() )
//                .addHeader("type","android")
//                .addHeader("uLat", TextUtils.isEmpty(lat)?"":lat)
//                .addHeader("uLng", TextUtils.isEmpty(lng)?"":lng)
//                .build();
        Logger.log(Logger.SCOPE.NETWORK,"## request url :"+originalRequest.url().toString());
//        Logger.log(Logger.SCOPE.NETWORK,"## request header token:"+LocalStoreManager.getUserToken());
//        Logger.log(Logger.SCOPE.NETWORK,"## request header phone:"+LocalStoreManager.getUserMobile());
//        Logger.log(Logger.SCOPE.NETWORK,"## request header version:"+AppUtil.getAppVersion());


        return chain.proceed(authorised);

    }

}
