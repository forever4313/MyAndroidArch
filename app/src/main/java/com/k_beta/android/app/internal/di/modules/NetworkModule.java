package com.k_beta.android.app.internal.di.modules;

import android.support.annotation.NonNull;
import com.k_beta.android.app.BuildConfig;
import com.k_beta.android.app.data.util.GsonUtil;
import com.k_beta.android.app.framework.NetWorkInterceptor;
import dagger.Module;
import dagger.Provides;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Kevin Dong on 2016/12/20.
 * 网络请求相关
 *
 */

@SuppressWarnings("ALL")
@Module
public class NetworkModule {

    private String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create(GsonUtil.getGson());
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory(){
        return  RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory converterFactory, RxJava2CallAdapterFactory rxJavaCallAdapterFactory){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory);
        builder.addCallAdapterFactory(rxJavaCallAdapterFactory);
        builder.client(client);
        return builder.build();
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(@NonNull @Named("logInterceptor") Interceptor logInterceptor, @NonNull @Named("netWorkInterceptor") Interceptor netWorkInterceptor){
        return new OkHttpClient.Builder()
                // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
                .addInterceptor(logInterceptor)
                .addInterceptor(netWorkInterceptor)
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
//                .sslSocketFactory(sslSocketFactory)
                // 信任所有主机名
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                // 这里我们使用host name作为cookie保存的key
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(HttpUrl.parse(url.host()), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    @Named("logInterceptor")
    Interceptor provideLogInterceptor(){
        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor it = new HttpLoggingInterceptor();
            it.setLevel(HttpLoggingInterceptor.Level.BASIC);
            return it;
        }
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request());
            }
        };
    }


    @Provides
    @Singleton
    @Named("netWorkInterceptor")
    Interceptor provideNetWorkInterceptor(){

        return new NetWorkInterceptor();
    }

}
