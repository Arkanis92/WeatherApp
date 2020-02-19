package com.example.weatherapp.repository.remote;

import com.example.weatherapp.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {

    @GET("/forecast/8655636f4b3d899ec4a94161cd346817/{latitude},{longitude}/")
    Call<WeatherResponse> getWeatherList(
            @Path("latitude") double latitude,
            @Path("longitude") double longitude
    );
}
