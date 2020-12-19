package com.example.weatherforyou.model.weather;

import com.example.weatherforyou.JSON.OpenWeather.Daily;

import java.util.List;

public interface WeatherInfo {


    String getTemperature();

    String getHumidity();

    String getVisibility();

    String getWindSpeed();

    String getWindDirection();

    String getPrecipitation();

    String getPressure();

    String getSunrise();

    String getSunset();

    List<HourWeather> getHourlyWeather();

    List<Daily> getDailyWeather();
}
