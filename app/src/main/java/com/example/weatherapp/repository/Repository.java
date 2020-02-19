package com.example.weatherapp.repository;

import com.example.weatherapp.model.WeatherResponse;
import com.example.weatherapp.repository.remote.ServiceInstance;
import com.example.weatherapp.repository.remote.WeatherService;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Repository {

    private Retrofit retrofit;

    private Repository() {
        retrofit = ServiceInstance.getInstance();
    }

    public static class RepositoryInstanceHolder {
        public static final Repository INSTANCE = new Repository();
    }

    public static Repository getInstance() {
        return RepositoryInstanceHolder.INSTANCE;
    }

    public Call<WeatherResponse> getWeather() {
        return retrofit.create(WeatherService.class)
                .getWeatherList(
                        37.8267,
                        -122.4233,
                        "minutely,flags"
                );
    }
}
