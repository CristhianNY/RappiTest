package com.cristhian.com.rappitest.api;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {

    public static Retrofit getMovieClient(Context context) {
        return new Retrofit.Builder().baseUrl(ConstantsServices.BASE_URL)
                .client(provideOkHttp(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }


    private static OkHttpClient provideOkHttp(Context context) {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(new Cache(context.getCacheDir(), 10 * 1024 *1024))
                .addNetworkInterceptor(provideLoggingInterceptor())
                .build();
    }

}
