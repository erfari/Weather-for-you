package com.example.weatherforyou.data.network;

import com.example.weatherforyou.JSON.WeatherBit.WeatherBCurrentList;
import com.example.weatherforyou.JSON.WeatherBit.WeatherBDailyList;
import com.example.weatherforyou.JSON.WeatherBit.WeatherBHourlyList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherBitService {
    @GET("forecast/daily")
    Call<WeatherBDailyList> getWeatherDaily(
            @Query("lat") Double latitude,
            @Query("lon") Double longitude,
            @Query("key") String apiKey,
            @Query("lang") String language,
            @Query("days") int days);

    @GET("forecast/hourly")
    Call<WeatherBHourlyList> getWeatherHourly(
            @Query("lat") Double latitude,
            @Query("lon") Double longitude,
            @Query("key") String apiKey,
            @Query("lang") String language,
            @Query("hours") int hours);

    @GET("current")
    Call<WeatherBCurrentList> getWeatherCurrent(
            @Query("lat") Double latitude,
            @Query("lon") Double longitude,
            @Query("key") String apiKey,
            @Query("lang") String language);
}
