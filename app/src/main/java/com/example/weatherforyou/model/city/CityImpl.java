package com.example.weatherforyou.model.city;

public class CityImpl implements City {

    private String name;

    private double lat;

    private double lon;

    public CityImpl(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTemperature() {
        return null;
    }

    @Override
    public double getLon() {
        return lon;
    }

    @Override
    public double getLat() {
        return lat;
    }

    @Override
    public String getWeatherIconRes() {
        return null;
    }
}
