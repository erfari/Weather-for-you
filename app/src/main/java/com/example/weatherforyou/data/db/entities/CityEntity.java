package com.example.weatherforyou.data.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "city")
public class CityEntity {

    @PrimaryKey
    @NotNull
    public String name;

    public double lat;

    public double lon;

    public int lastUpdate;


    public CityEntity(@NotNull String name, Double lat, Double lon, int lastUpdate) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.lastUpdate = lastUpdate;
    }
}
