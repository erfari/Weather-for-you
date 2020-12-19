package com.example.weatherforyou.data.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "weather")
public class WeatherEntity {


    private int id;

    private Double lat;

    private Double lon;

    @PrimaryKey
    @NotNull
    private String cityName;

    private String description;

    private Double temperature;

    private int lastUpdateTime;

    private String iconId;

    private InfoEntity infoEntity;

    private List<DailyEntity> dailyWeather;

    private List<HourlyEntity> hourlyWeather;

    public WeatherEntity(Double lat, Double lon, String cityName, String description, Double temperature, int lastUpdateTime, String iconId, InfoEntity infoEntity, List<DailyEntity> dailyWeather, List<HourlyEntity> hourlyWeather) {
        this.lat = lat;
        this.lon = lon;
        this.cityName = cityName;
        this.description = description;
        this.temperature = temperature;
        this.lastUpdateTime = lastUpdateTime;
        this.iconId = iconId;
        this.infoEntity = infoEntity;
        this.dailyWeather = dailyWeather;
        this.hourlyWeather = hourlyWeather;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public List<DailyEntity> getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(List<DailyEntity> dailyWeather) {
        this.dailyWeather = dailyWeather;
    }

    public List<HourlyEntity> getHourlyWeather() {
        return hourlyWeather;
    }

    public void setHourlyWeather(List<HourlyEntity> hourlyWeather) {
        this.hourlyWeather = hourlyWeather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(int lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public InfoEntity getInfoEntity() {
        return infoEntity;
    }

    public void setInfoEntity(InfoEntity infoEntity) {
        this.infoEntity = infoEntity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

}
