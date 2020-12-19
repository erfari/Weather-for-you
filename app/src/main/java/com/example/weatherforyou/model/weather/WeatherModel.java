package com.example.weatherforyou.model.weather;

import java.util.List;

public interface WeatherModel {

    Double getLat();

    Double getLon();

    String getCityName();

    String getTemperature();

    String getDescription();

    String getIconId();

    int getLastUpdateTime();

    WeatherInfo getModelInfo();

    List<WeatherInfoUIModel> getWeatherInfoUI();

    List<HourWeather> getHourlyWeather();

    List<DailyWeather> getDailyWeather();

}
