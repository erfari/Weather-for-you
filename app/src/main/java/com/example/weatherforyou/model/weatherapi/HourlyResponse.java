package com.example.weatherforyou.model.weatherapi;

public interface HourlyResponse {

    int getCity();

    int getTime();

    Double getTemp();

    String getIconWithUrl();
}
