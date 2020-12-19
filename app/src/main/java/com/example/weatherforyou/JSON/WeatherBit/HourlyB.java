package com.example.weatherforyou.JSON.WeatherBit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HourlyB {
    @SerializedName("ts")
    @Expose
    private Integer dtHourly;
    @SerializedName("temp")
    @Expose
    private Double temperature;

    public Integer getDtHourly() {
        return dtHourly;
    }

    public Double getTemperature() {
        return temperature;
    }
}
