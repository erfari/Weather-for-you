package com.example.weatherforyou.data.network;

import com.example.weatherforyou.JSON.OpenWeather.Weather;
import com.example.weatherforyou.JSON.Yandex.WeatherY;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface YandexWeatherService {

    @Headers({"X-Yandex-API-Key: bb158e16-f19a-4fee-8e03-810734de704d"})
    @GET("forecast")
    Call<WeatherY> getWeather(
            @Query("lat") Double latitude,
            @Query("lon") Double longitude,
            @Query("appid") String apiKey,
            @Query("lang") String language);

}
