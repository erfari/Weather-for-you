package com.example.weatherforyou.model.weatherapi;

import com.bumptech.glide.request.target.ThumbnailImageViewTarget;

public class DailyResponseImpl implements DailyResponse {


    private int city;

    private int time;

    private Double minTemp;

    private Double maxTemp;

    private String icon;


    public DailyResponseImpl() {
        this.city = 0;
        this.time = 0;
        this.minTemp = 0.0;
        this.maxTemp = 0.0;
        this.icon = "";
    }

    public DailyResponseImpl(int city, int time, Double minTemp, Double maxTemp, String icon) {
        this.city = city;
        this.time = time;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.icon = icon;
    }

    @Override
    public int getCity() {
        return city;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public Double getMinTemp() {
        return minTemp;
    }

    @Override
    public Double getMaxTemp() {
        return maxTemp;
    }

    @Override
    public String getIconWithUrl() {
        return icon;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    DailyResponseImpl plus(DailyResponse response){

        this.minTemp = this.minTemp + response.getMinTemp();

        this.maxTemp = this.maxTemp + response.getMaxTemp();


        return this;
    }

    DailyResponseImpl addInfo(DailyResponse info){

        this.city = info.getCity();

        this.time = info.getTime();

        this.icon = info.getIconWithUrl();

        return this;
    }

    DailyResponseImpl div(int value){

        this.minTemp = this.minTemp/value;

        this.maxTemp = this.maxTemp/value;

        return this;
    }
}
