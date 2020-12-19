package com.example.weatherforyou.JSON.WeatherBit;

import com.example.weatherforyou.model.weather.DailyWeather;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBDailyList{
    @SerializedName("data")
    @Expose
    private List<DailyB> dailyB;

    @Override
    public String toString() {
        return "WeatherB{" +
                "dailyB=" + dailyB.get(0).toString() +
                '}';
    }

    public List<DailyB> getDailyB() {
        return dailyB;
    }

    public void setDailyB(List<DailyB> dailyB) {
        this.dailyB = dailyB;
    }
}
