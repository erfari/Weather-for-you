package com.example.weatherforyou.JSON.Yandex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NightTemp {

    @SerializedName("temp_min")
    @Expose
    private Double minTemp;

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }
}
