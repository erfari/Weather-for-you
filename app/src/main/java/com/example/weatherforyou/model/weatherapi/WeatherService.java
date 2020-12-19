package com.example.weatherforyou.model.weatherapi;

import androidx.lifecycle.LiveData;

import com.example.weatherforyou.model.city.City;

public interface WeatherService {


    LiveData<ForecastResponse> getResponse();

    void update(City city) throws InterruptedException;
}
