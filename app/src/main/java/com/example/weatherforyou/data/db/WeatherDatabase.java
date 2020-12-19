package com.example.weatherforyou.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weatherforyou.data.db.dao.CityDao;
import com.example.weatherforyou.data.db.dao.WeatherDao;
import com.example.weatherforyou.data.db.entities.CityEntity;
import com.example.weatherforyou.data.db.entities.WeatherEntity;
import com.example.weatherforyou.data.db.typeconverter.WeatherConverter;

@Database(entities = {WeatherEntity.class, CityEntity.class}, version = 1)
@TypeConverters({WeatherConverter.class})
public abstract class WeatherDatabase extends RoomDatabase {
    abstract public WeatherDao getWeatherDao();
    abstract public CityDao getCityDao();
}
