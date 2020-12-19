package com.example.weatherforyou.data.db.typeconverter;

import androidx.room.TypeConverter;

import com.example.weatherforyou.data.db.entities.DailyEntity;
import com.example.weatherforyou.data.db.entities.HourlyEntity;
import com.example.weatherforyou.data.db.entities.InfoEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.List;

public class WeatherConverter {

    @TypeConverter
    public  String fromHourlyEntity(List<HourlyEntity> hourlyList){

        Gson gson = new Gson();

        String result = gson.toJson(hourlyList);

        return result;

    }

    @TypeConverter
    public List<HourlyEntity> toHourlyEntity(String weatherString){
        return new Gson().fromJson(weatherString, new TypeToken<List<HourlyEntity>>(){}.getType());
    }

    @TypeConverter
    public  String fromDailyEntity(List<DailyEntity> hourlyList){

        Gson gson = new Gson();

        String result = gson.toJson(hourlyList);

        return result;

    }

    @TypeConverter
    public List<DailyEntity> toDailyEntity(String weatherString){
        return new Gson().fromJson(weatherString, new TypeToken<List<DailyEntity>>(){}.getType());
    }


    @TypeConverter
    public String fromInfoEntity(InfoEntity infoEntity){

        Gson gson = new Gson();

        String result = gson.toJson(infoEntity);

        return result;

    }

    @TypeConverter
    public InfoEntity toInfoEntity(String infoEntity){
        return new Gson().fromJson(infoEntity, new TypeToken<InfoEntity>(){}.getType());
    }
}
