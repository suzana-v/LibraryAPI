package com.svulinovic.library.retrofit;

import com.svulinovic.library.retrofit.microblink.MicroblinkAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitHelper {

    private static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor(System.out::println).setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final OkHttpClient baseOkHttpClient = new OkHttpClient();

    private static final GsonConverterFactory gsonFactory = GsonConverterFactory.create();

    public static MicroblinkAPI getMicroblinkAPI(String baseUrl) {
        OkHttpClient.Builder httpClient = baseOkHttpClient.newBuilder();

        httpClient.interceptors().clear();
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonFactory)
                .client(httpClient.build())
                .build();

        return retrofit.create(MicroblinkAPI.class);
    }
}
