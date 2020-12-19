package com.example.weatherforyou.model.weatherapi;

public interface DailyResponse {

    int getCity();

    int getTime();

    Double getMinTemp();

    Double getMaxTemp();

    String getIconWithUrl();
}
