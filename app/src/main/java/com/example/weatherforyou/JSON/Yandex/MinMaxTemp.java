package com.example.weatherforyou.JSON.Yandex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MinMaxTemp {

    @SerializedName("day")
    @Expose
    private DayTemp dayTemp;
    @SerializedName("night")
    @Expose
    private NightTemp nightTemp;

    public DayTemp getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(DayTemp dayTemp) {
        this.dayTemp = dayTemp;
    }

    public NightTemp getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(NightTemp nightTemp) {
        this.nightTemp = nightTemp;
    }
}
