package com.example.weatherforyou.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weatherforyou.data.db.entities.WeatherEntity;

import java.util.List;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weather ORDER BY cityName limit 1")
    WeatherEntity getCurrentCity();

    @Query("SELECT * FROM weather WHERE cityName =:cityName")
    LiveData<WeatherEntity> getBy(String cityName);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherEntity weather);

    @Update
    void update(WeatherEntity weather);
}
