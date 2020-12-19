package com.example.weatherforyou.model.city;

import androidx.annotation.ColorInt;

import java.util.zip.Inflater;

public class FavoriteCityImpl implements FavoriteCity {

    String name;

    String someTime;

    String temp;


    int color;

    public FavoriteCityImpl(String name, String someTime, String temp) {
        this.name = name;
        this.someTime = someTime;
        this.temp = temp;
    }

    public FavoriteCityImpl(String name, String someTime, String temp, int color) {
        this.name = name;
        this.someTime = someTime;
        this.temp = temp;
        this.color = color;
    }

    @Override
    public String getSomeTime() {
        return someTime;
    }

    @Override
    public String getCityName() {
        return name;
    }

    @Override
    public String getTemp() {
        return temp;
    }

    @Override
    public int backColor() {
        return color;
    }


}
