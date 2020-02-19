package com.example.weatherapp.repository.remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceInstance {
    private static final String BASE_URL = "https://api.darksky.net/";

    public ServiceInstance() {
    }

    private static class RetrofitInstanceHolder {
        private static final Retrofit INSTANCE =
                new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getInstance() {
        return RetrofitInstanceHolder.INSTANCE;
    }
}
