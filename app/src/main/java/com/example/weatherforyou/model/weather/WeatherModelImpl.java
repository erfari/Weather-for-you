package com.example.weatherforyou.model.weather;

import java.util.List;

public class WeatherModelImpl implements WeatherModel {

    Double lat;

    Double lon;

    String cityName;

    int lastUpdateTime;

    String temperature;

    String description;

    String iconId;

    WeatherInfo modelInfo;

    List<HourWeather> hourlyWeather;

    List<DailyWeather> dailyWeather;

    List<WeatherInfoUIModel> weatherInfoUIModels;

    public WeatherModelImpl(Double lat, Double lon, String cityName, int lastUpdateTime, String temperature, String description, String iconId, WeatherInfo modelInfo, List<HourWeather> hourlyWeather, List<DailyWeather> dailyWeather, List<WeatherInfoUIModel> weatherInfoUIModels) {
        this.lat = lat;
        this.lon = lon;
        this.cityName = cityName;
        this.lastUpdateTime = lastUpdateTime;
        this.temperature = temperature;
        this.description = description;
        this.iconId = iconId;
        this.modelInfo = modelInfo;
        this.hourlyWeather = hourlyWeather;
        this.dailyWeather = dailyWeather;
        this.weatherInfoUIModels = weatherInfoUIModels;
    }


    @Override
    public Double getLat() {
        return lat;
    }

    @Override
    public Double getLon() {
        return lon;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public String getTemperature() {
        return temperature;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getIconId() {
        return iconId;
    }

    @Override
    public int getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Override
    public WeatherInfo getModelInfo() {
        return modelInfo;
    }

    @Override
    public List<WeatherInfoUIModel> getWeatherInfoUI() {
        return weatherInfoUIModels;
    }


    @Override
    public List<HourWeather> getHourlyWeather() {
        return hourlyWeather;
    }

    @Override
    public List<DailyWeather> getDailyWeather() {
        return dailyWeather;
    }
}

