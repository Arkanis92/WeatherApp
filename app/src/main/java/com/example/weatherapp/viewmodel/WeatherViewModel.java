package com.example.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.model.WeatherResponse;
import com.example.weatherapp.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends AndroidViewModel {

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<WeatherResponse> weather = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    private Repository repo = Repository.getInstance();

    public void getWeatherObservable() {
        repo.getWeather()
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        weather.postValue(response.body());
                        error.postValue("");
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        weather.postValue(new WeatherResponse());
                        error.postValue(t.getMessage());
                    }
                });
    }

    public LiveData<WeatherResponse> getWeatherLiveData() {
        return weather;
    }

    public LiveData<String> getErrorLiveData() {
        return error;
    }
}
