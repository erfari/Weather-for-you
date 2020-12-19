package com.example.weatherforyou.JSON.WeatherBit;

public class WeatherB {

    WeatherBCurrentList weatherBCurrentList;

    WeatherBHourlyList weatherBHourlyList;

    WeatherBDailyList weatherBDailyList;

    public WeatherB(WeatherBCurrentList weatherBCurrentList, WeatherBHourlyList weatherBHourlyList, WeatherBDailyList weatherBDailyList) {
        this.weatherBCurrentList = weatherBCurrentList;
        this.weatherBHourlyList = weatherBHourlyList;
        this.weatherBDailyList = weatherBDailyList;
    }

    public WeatherBCurrentList getWeatherBCurrentList() {
        return weatherBCurrentList;
    }

    public void setWeatherBCurrentList(WeatherBCurrentList weatherBCurrentList) {
        this.weatherBCurrentList = weatherBCurrentList;
    }

    public WeatherBHourlyList getWeatherBHourlyList() {
        return weatherBHourlyList;
    }

    public void setWeatherBHourlyList(WeatherBHourlyList weatherBHourlyList) {
        this.weatherBHourlyList = weatherBHourlyList;
    }

    public WeatherBDailyList getWeatherBDailyList() {
        return weatherBDailyList;
    }

    public void setWeatherBDailyList(WeatherBDailyList weatherBDailyList) {
        this.weatherBDailyList = weatherBDailyList;
    }
}
