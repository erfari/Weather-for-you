package com.example.weatherforyou.JSON.WeatherBit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBHourlyList {
    @SerializedName("data")
    @Expose
    private List<HourlyB> ts;

    public List<HourlyB> getTs() {
        return ts;
    }

    public void setTs(List<HourlyB> ts) {
        this.ts = ts;
    }
}
