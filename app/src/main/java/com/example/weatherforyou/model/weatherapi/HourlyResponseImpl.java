package com.example.weatherforyou.model.weatherapi;

public class HourlyResponseImpl implements HourlyResponse {


    private int city;

    private int time;

    private Double temp;

    private String icon;

    public HourlyResponseImpl() {
        this.city = 0;
        this.time = 0;
        this.temp = 0.0;
        this.icon = "";
    }

    public HourlyResponseImpl(int city, int time, Double temp, String icon) {
        this.city = city;
        this.time = time;
        this.temp = temp;
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
    public Double getTemp() {
        return temp;
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

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public HourlyResponseImpl plus(HourlyResponse hourlyResponse){
        this.temp = this.temp + hourlyResponse.getTemp();
        return this;
    }

    public HourlyResponseImpl addInfo(HourlyResponse hourlyResponse){
        this.city = hourlyResponse.getCity();
        this.time = hourlyResponse.getTime();
        this.icon = hourlyResponse.getIconWithUrl();
        return this;
    }

    public HourlyResponseImpl div(int value){
        this.temp = this.temp/value;
        return this;
    }
}
