package com.example.weatherforyou.JSON.Yandex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyY {
    @SerializedName("date_ts")
    @Expose
    private Integer dtDay;
    @SerializedName("sunrise")
    @Expose
    private String sunrise;
    @SerializedName("sunset")
    @Expose
    private String sunset;
    @SerializedName("parts")
    @Expose
    private MinMaxTemp minMaxTemp;
    @SerializedName("hours")
    @Expose
    private List<HourlyTempY> hourlyTempY;


    public Integer getDtDay() {
        return dtDay;
    }

    public void setDtDay(Integer dtDay) {
        this.dtDay = dtDay;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public MinMaxTemp getMinMaxTemp() {
        return minMaxTemp;
    }

    public void setMinMaxTemp(MinMaxTemp minMaxTemp) {
        this.minMaxTemp = minMaxTemp;
    }

    public List<HourlyTempY> getHourlyTempY() {
        return hourlyTempY;
    }

    public void setHourlyTempY(List<HourlyTempY> hourlyTempY) {
        this.hourlyTempY = hourlyTempY;
    }
}
