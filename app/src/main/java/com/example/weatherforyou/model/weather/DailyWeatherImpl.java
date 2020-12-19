package com.example.weatherforyou.model.weather;

public class DailyWeatherImpl implements DailyWeather{

    public DailyWeatherImpl(String date, String minTemperature, String maxTemperature, String iconIdWithUrl){
        this.date = date;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.iconIdWithUrl = iconIdWithUrl;
    }

    private String date;

    private String minTemperature;

    private String maxTemperature;

    private String iconIdWithUrl;

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getMinTemperature() {
        return minTemperature;
    }

    @Override
    public String getMaxTemperature() {
        return maxTemperature;
    }

    @Override
    public String getIconIdWithUrl() {
        return iconIdWithUrl;
    }


}

