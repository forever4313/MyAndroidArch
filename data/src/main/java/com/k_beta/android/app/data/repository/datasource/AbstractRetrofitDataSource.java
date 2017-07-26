package com.k_beta.android.app.data.repository.datasource;

import com.k_beta.android.app.data.net.RestApi;
import com.k_beta.android.app.data.util.GsonUtil;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kevin Dong on 2017/7/21.
 */
public abstract  class  AbstractRetrofitDataSource {
    protected RestApi restApi;
    protected Retrofit retrofit;

    public AbstractRetrofitDataSource(){

        retrofit = getRetrofitInstance(true);
        restApi = retrofit.create(RestApi.class);

    }


    public Retrofit getRetrofitInstance(boolean userRxJava){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(getApiUrl())
                .addConverterFactory(GsonConverterFactory.create(GsonUtil.getGson()));
        if(userRxJava){
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        builder.client(getClient());
        return builder.build();
    }

    public OkHttpClient getClient(){

//        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
//        SSLSocketFactory sslSocketFactory = null;
//
//        try {
//            // 这里直接创建一个不做证书串验证的TrustManager
//            final TrustManager[] trustAllCerts = new TrustManager[]{
//                    new X509TrustManager() {
//                        @Override
//                        public void checkClientTrusted(X509Certificate[] chain, String authType)
//                                throws CertificateException {
//                        }
//
//                        @Override
//                        public void checkServerTrusted(X509Certificate[] chain, String authType)
//                                throws CertificateException {
//                        }
//
//                        @Override
//                        public X509Certificate[] getAcceptedIssuers() {
//                            return new X509Certificate[]{};
//                        }
//                    }
//            };
//
//            // Install the all-trusting trust manager
//            final SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//            // Create an ssl socket factory with our all-trusting manager
//            sslSocketFactory = sslContext.getSocketFactory();
//        } catch (Exception e) {
//            Logger.log(Logger.SCOPE.NETWORK, e.getMessage());
//        }

        return new OkHttpClient.Builder()
                // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
//                .addInterceptor(NetWorkInterceptorConfig.getLogInterceptor())
//                .addInterceptor(NetWorkInterceptorConfig.getNetWorkInterceptor())
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


    public abstract String getApiUrl();

}
