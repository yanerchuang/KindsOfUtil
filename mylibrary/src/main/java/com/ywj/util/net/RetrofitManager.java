package com.ywj.util.net;

import android.content.Context;
import android.support.annotation.Nullable;

import com.ywj.util.app.Config;
import com.ywj.util.net.log.ArmsRequestInterceptor;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager mRetrofitManager;
    private static Retrofit mRetrofit;
    private static String BASE_URL;
    private static final int[] lock = new int[0];
    private static Interceptor requestInterceptor;

    public static OkHttpClient okHttpClient;
    private static RxErrorHandler rxErrorHandler;

    public static void setInterceptor(Interceptor interceptor) {
        requestInterceptor = interceptor;
    }


    private RetrofitManager() {
        initRetrofit();
    }

    public static void init(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public static void intOkHttpClient(OkHttpClient okHttpClient) {
        RetrofitManager.okHttpClient = okHttpClient;
    }

    public static RxErrorHandler initResponseErrorListener(Context context, ResponseErrorListener listener) {
        rxErrorHandler = RxErrorHandler
                .builder()
                .with(context)
                .responseErrorListener(listener)
                .build();
        return rxErrorHandler;
    }

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            synchronized (lock) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofit;
    }

//    public static Api getInstance() {
//        if (mRetrofitManager == null) {
//            synchronized (lock) {
//                if (mRetrofitManager == null) {
//                    mRetrofitManager = new RetrofitManager();
//                }
//            }
//        }
//        return mRetrofit.create(Api.class);
//    }

    @Nullable
    public static RxErrorHandler getRxErrorHandler() {
        return rxErrorHandler;
    }


    private void initRetrofit() {

        if (okHttpClient == null) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (requestInterceptor == null) {

                requestInterceptor = new ArmsRequestInterceptor();
                ArmsRequestInterceptor.Level level;
                //debug 模式打印log
                if (Config.DEBUG) {
                    level = ArmsRequestInterceptor.Level.ALL;
                } else {
                    level = ArmsRequestInterceptor.Level.NONE;
                }
                ((ArmsRequestInterceptor) requestInterceptor).setLevel(level);

            }
            builder.addInterceptor(requestInterceptor); //添加请求过滤


            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);
            builder.retryOnConnectionFailure(true);
//        builder.sslSocketFactory(createSSLSocketFactory());
//        builder.sslSocketFactory(createSSLSocketFactory(),new TrustAllCerts());
//        builder.hostnameVerifier((hostname, session) -> true);
            //信任规则全部信任
            getUnsafeOkHttpClient(builder);
            okHttpClient = builder.build();
        }


        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static Map<String, Object> getMap() {
        return new HashMap<String, Object>();
    }

    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            if (Config.DEBUG) {
                e.printStackTrace();
            }
        }

        return ssfFactory;
    }

    private class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /***
     * 不验证HTTPS证书
     */
    private void getUnsafeOkHttpClient(OkHttpClient.Builder okhttpBuilder) {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            okhttpBuilder.sslSocketFactory(sslSocketFactory);
            okhttpBuilder.hostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
