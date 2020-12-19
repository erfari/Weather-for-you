package com.example.weatherforyou.data.network;


import com.example.weatherforyou.JSON.OpenWeather.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {
    @GET("onecall")
    Call<Weather> getWeather(
            @Query("lat") Double latitude,
            @Query("lon") Double longitude,
            @Query("appid") String apiKey,
            @Query("units") String units,
            @Query("lang") String language,
            @Query("exclude") String exclude);

    @GET("onecall")
    Call<Weather> getWeatherByCity(
            @Query("q")String cityName,
            @Query("appid") String apiKey,
            @Query("units") String units,
            @Query("lang") String language,
            @Query("exclude") String exclude);
}


