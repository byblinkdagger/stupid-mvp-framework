package com.oragee.groups.net;

import android.content.Context;
import android.util.Log;

import com.oragee.groups.util.PreferencesUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lucky on 2017/3/29.
 */
public class RetrofitClient {

    public static final String Base_URL = "http://139.196.110.236:3030";
    private static final int DEFAULT_TIMEOUT = 5;

    private static volatile Retrofit retrofit;
    private static Cache cache = null;

    /**
     * 双重检索保证线程安全
     * @return
     */
    public static GroupsApi getInstance(final Context context){
        if (retrofit == null) {
            synchronized (RetrofitClient.class){
                if (retrofit == null) {

                    try {
                        if (cache == null) {
                            cache = new Cache(new File(context.getCacheDir(), "my_cache"), 10 * 1024 * 1024);
                        }
                    } catch (Exception e) {
                        Log.e("OKHttp", "Could not create http cache", e);
                    }

                    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                            .cache(cache)
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                            .addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request original = chain.request();
                                    Request.Builder builder = original.newBuilder();
                                    builder.addHeader("Token", PreferencesUtils.getValueOfSharedPreferences(context, "token", ""));
                                    return chain.proceed(builder.build());
                                }
                            });

                    if (true) {
                        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        clientBuilder.addInterceptor(interceptor);
                    }

                    retrofit=new Retrofit.Builder().baseUrl(Base_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(clientBuilder.build())
                            .build();
                }
            }
        }
        return retrofit.create(GroupsApi.class);
    }

}
