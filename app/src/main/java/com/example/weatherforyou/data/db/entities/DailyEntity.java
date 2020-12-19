package com.example.weatherforyou.data.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "daily")
public class DailyEntity {

    private int city;
    private int time;
    private Double minTemp;
    private Double maxTemp;
    private String iconIdWithUrl;


    public DailyEntity(int city, int time, Double minTemp, Double maxTemp, String iconIdWithUrl) {
        this.city = city;
        this.time = time;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.iconIdWithUrl = iconIdWithUrl;
    }


    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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

    public String getIconIdWithUrl() {
        return iconIdWithUrl;
    }

    public void setIconIdWithUrl(String iconIdWithUrl) {
        this.iconIdWithUrl = iconIdWithUrl;
    }
}
