package com.example.weatherforyou.model.weather;

public class HourWeatherImpl implements HourWeather {

    public HourWeatherImpl(String time, String temperature, String iconIdWithUrl) {
        this.time = time;
        this.temperature = temperature;
        this.iconIdWithUrl = iconIdWithUrl;
    }

    private String time;

    private String temperature;

    private String iconIdWithUrl;

    @Override
    public String getHour() {
        return time;
    }

    @Override
    public String getTemperature() {
        return temperature;
    }

    @Override
    public String getIconIdWithUrl() {
        return iconIdWithUrl;
    }
}
