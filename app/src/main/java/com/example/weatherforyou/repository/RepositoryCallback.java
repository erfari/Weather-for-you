package com.example.weatherforyou.repository;

import com.example.weatherforyou.JSON.OpenWeather.Weather;

public interface RepositoryCallback{

   void onSuccess(Weather weather);

   void onFailure();
}
