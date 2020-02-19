package com.example.weatherapp.model;


import com.google.gson.annotations.SerializedName;


public class DataItem {

    @SerializedName("precipProbability")
    private double precipProbability;

    @SerializedName("precipIntensity")
    private double precipIntensity;

    @SerializedName("time")
    private int time;

    @SerializedName("precipIntensityError")
    private double precipIntensityError;

    @SerializedName("precipType")
    private String precipType;

    @SerializedName("temperature")
    private double temperature;

    @SerializedName("icon")
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @SerializedName("temperatureHigh")
    private double temperatureHigh;

    @SerializedName("temperatureLow")
    private double temperatureLow;

    public double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public void setPrecipProbability(double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipIntensity(double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setPrecipIntensityError(double precipIntensityError) {
        this.precipIntensityError = precipIntensityError;
    }

    public double getPrecipIntensityError() {
        return precipIntensityError;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public String getPrecipType() {
        return precipType;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "precipProbability = '" + precipProbability + '\'' +
                        ",precipIntensity = '" + precipIntensity + '\'' +
                        ",time = '" + time + '\'' +
                        ",precipIntensityError = '" + precipIntensityError + '\'' +
                        ",precipType = '" + precipType + '\'' +
                        "}";
    }
}