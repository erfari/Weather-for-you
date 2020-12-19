package com.example.weatherforyou.data.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hourly")
public class HourlyEntity {

    public HourlyEntity(int city, int time, Double temperature, String iconIdWithUrl) {
        this.city = city;
        this.time = time;
        this.temperature = temperature;
        this.iconIdWithUrl = iconIdWithUrl;
    }

    @PrimaryKey
    private int city;

    private int time;

    private Double temperature;

    private String iconIdWithUrl;

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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getIconIdWithUrl() {
        return iconIdWithUrl;
    }

    public void setIconIdWithUrl(String iconIdWithUrl) {
        this.iconIdWithUrl = iconIdWithUrl;
    }
}
