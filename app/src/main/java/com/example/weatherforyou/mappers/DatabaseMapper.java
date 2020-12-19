package com.example.weatherforyou.mappers;

import com.example.weatherforyou.JSON.OpenWeather.Daily;
import com.example.weatherforyou.JSON.OpenWeather.Hourly;
import com.example.weatherforyou.JSON.OpenWeather.Weather;
import com.example.weatherforyou.JSON.WeatherBit.WeatherB;
import com.example.weatherforyou.data.db.entities.DailyEntity;
import com.example.weatherforyou.data.db.entities.HourlyEntity;
import com.example.weatherforyou.data.db.entities.InfoEntity;
import com.example.weatherforyou.data.db.entities.WeatherEntity;
import com.example.weatherforyou.model.city.City;
import com.example.weatherforyou.model.weatherapi.DailyResponse;
import com.example.weatherforyou.model.weatherapi.ForecastResponse;

import java.util.ArrayList;

public class DatabaseMapper {

    private City city;

    public DatabaseMapper(City city) {
        this.city = city;
    }

      public WeatherEntity toDatabaseModel(Weather weather) {

        Double lat = weather.getLat();
        Double lon = weather.getLon();

        String cityName = city.getName();
        String description = weather.getCurrent().getWeather().get(0).getDescription();
        String iconId = weather.getCurrent().getWeather().get(0).getIcon();
        Double temperature = weather.getCurrent().getTemp();
        int lastUpdateTime = weather.getCurrent().getDt();
        String rain;
        if (weather.getCurrent().getRain() != null) {
            rain = weather.getCurrent().getRain().get1h().toString();
        } else {
            rain = "0";
        }
        InfoEntity infoEntity = new InfoEntity(
                weather.getCurrent().getHumidity().toString(),
                weather.getCurrent().getVisibility().toString(),
                weather.getCurrent().getWindSpeed().toString(),
                weather.getCurrent().getWindDeg().toString(),
                rain,
                weather.getCurrent().getPressure().toString(),
                weather.getCurrent().getSunrise(),
                weather.getCurrent().getSunset()
        );

        ArrayList<DailyEntity> dailyEntityList = new ArrayList();
        ArrayList<HourlyEntity> hourlyEntityList = new ArrayList();

        for (Daily daily : weather.getDaily()) {
            dailyEntityList.add(new DailyEntity(1, daily.getDt(), daily.getTemp().getMin(), daily.getTemp().getMax(), daily.getWeather().get(0).getIcon()));
        }

        for (Hourly hourly : weather.getHourly()) {
            hourlyEntityList.add(new HourlyEntity(1, hourly.getDt(), hourly.getTemp(), hourly.getWeather().get(0).getIcon()));
        }

        return new WeatherEntity(lat, lon, cityName, description, temperature, lastUpdateTime, iconId, infoEntity, dailyEntityList, hourlyEntityList);
    }




}
