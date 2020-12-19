package com.example.weatherforyou.repository;

import com.example.weatherforyou.JSON.OpenWeather.Weather;
import com.example.weatherforyou.JSON.WeatherBit.WeatherB;

public interface WBRepositoryCallback {
    void onSuccess(WeatherB weatherB);

    void onFailure();
}
