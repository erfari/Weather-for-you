package com.example.weatherforyou.JSON.Yandex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HourlyTempY {
    @SerializedName("hour_ts")
    @Expose
    private Integer dtHour;
    @SerializedName("temp")
    @Expose
    private Double tempHour;



    public Integer getDtHour() {
        return dtHour;
    }

    public void setDtHour(Integer dtHour) {
        this.dtHour = dtHour;
    }

    public Double getTempHour() {
        return tempHour;
    }

    public void setTempHour(Double tempHour) {
        this.tempHour = tempHour;
    }

}
