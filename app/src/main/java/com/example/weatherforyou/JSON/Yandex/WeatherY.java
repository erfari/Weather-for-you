package com.example.weatherforyou.JSON.Yandex;

import com.example.weatherforyou.JSON.OpenWeather.Daily;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherY {

    @SerializedName("now")
    @Expose
    private Integer time;
    @SerializedName("fact")
    @Expose
    private CurrentY currentY;
    @SerializedName("forecasts")
    @Expose
    private List<DailyY> dailyY;





    public List<DailyY> getDailyY() {
        return dailyY;
    }

    public void setDailyY(List<DailyY> dailyY) {
        this.dailyY = dailyY;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public CurrentY getCurrentY() {
        return currentY;
    }

    public void setCurrentY(CurrentY currentY) {
        this.currentY = currentY;
    }
}
