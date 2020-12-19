package com.example.weatherforyou.JSON.WeatherBit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBCurrentList {
    @SerializedName("data")
    @Expose
    private List<CurrentB> currentB;

    public List<CurrentB> getCurrentB() {
        return currentB;
    }

    public void setCurrentB(List<CurrentB> currentB) {
        this.currentB = currentB;
    }
}
