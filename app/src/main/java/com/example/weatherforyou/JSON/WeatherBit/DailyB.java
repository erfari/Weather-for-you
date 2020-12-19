package com.example.weatherforyou.JSON.WeatherBit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyB {

    @SerializedName("moonrise_ts")
    @Expose
    private Integer dtDaily;
    @SerializedName("low_temp")
    @Expose
    private Double minTemp;
    @SerializedName("max_temp")
    @Expose
    private Double maxTemp;

    @Override
    public String
    toString() {
        return "DailyB{" +
                "dtDaily=" + dtDaily +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                '}';
    }

    public Integer getDtDaily() {
        return dtDaily;
    }

    public void setDtDaily(Integer dtDaily) {
        this.dtDaily = dtDaily;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }
}
