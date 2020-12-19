package com.example.weatherforyou.data.network;

import android.util.Log;

import com.example.weatherforyou.JSON.OpenWeather.Weather;
import com.example.weatherforyou.JSON.Yandex.WeatherY;
import com.example.weatherforyou.repository.RepositoryCallback;
import com.example.weatherforyou.repository.YaRepositoryCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class YandexWeatherControler implements Callback<WeatherY> {

    static final String BASE_URL = "https://api.weather.yandex.ru/v2/";

    YaRepositoryCallback yaRepositoryCallback;

    public void start(YaRepositoryCallback yaRepositoryCallback, double lat, double lon) {
        Gson gson = new GsonBuilder().setLenient().create();
        this.yaRepositoryCallback = yaRepositoryCallback;

//        this.repositoryCallback = repositoryCallback;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weather.yandex.ru/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        YandexWeatherService service = retrofit.create(YandexWeatherService.class);

        Call<WeatherY> call = service.getWeather(
                lat,
                lon,
                "c2b8a9ca1d4763ce4308de9c3ebcc05d",
                Locale.getDefault().toString()
        );
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<WeatherY> call, Response<WeatherY> response) {
        if (response.isSuccessful()) {
            WeatherY weatherY = response.body();
            yaRepositoryCallback.onSuccess(weatherY);
        }

    }

    @Override
    public void onFailure(Call<WeatherY> call, Throwable t) {
        t.printStackTrace();
    }

}

