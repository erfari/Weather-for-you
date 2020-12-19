package com.example.weatherforyou.data.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weatherforyou.data.db.entities.CityEntity;
import com.example.weatherforyou.data.db.entities.WeatherEntity;
import com.example.weatherforyou.model.city.City;

import java.util.List;

@Dao
public interface CityDao {

    @Query("SELECT * FROM city")
    public List<CityEntity> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(CityEntity city);

    @Update
    public void update(CityEntity city);

    @Delete
    public void delete(CityEntity city);

}
