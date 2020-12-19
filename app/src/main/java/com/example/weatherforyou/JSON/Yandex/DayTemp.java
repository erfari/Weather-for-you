package com.example.weatherforyou.JSON.Yandex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DayTemp {
    @SerializedName("temp_max")
    @Expose
    private Double maxTemp;

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.maxTemp = minTemp;
    }
}
