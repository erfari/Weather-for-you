package com.example.weatherforyou.data.network;

import android.util.Log;

import com.example.weatherforyou.JSON.OpenWeather.Weather;
import com.example.weatherforyou.JSON.WeatherBit.WeatherB;
import com.example.weatherforyou.JSON.WeatherBit.WeatherBCurrentList;
import com.example.weatherforyou.JSON.WeatherBit.WeatherBDailyList;
import com.example.weatherforyou.JSON.WeatherBit.WeatherBHourlyList;
import com.example.weatherforyou.repository.WBRepositoryCallback;
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

public class WeatherBitController {

    WeatherB weatherB;
    WeatherBCurrentList weatherBCurrentList;
    WeatherBHourlyList weatherBHourlyList;
    WeatherBDailyList weatherBDailyList;
    int x = 0;

    WBRepositoryCallback wbRepositoryCallback;


    public void start(WBRepositoryCallback wbRepositoryCallback, double lat, double lon) {

        this.wbRepositoryCallback = wbRepositoryCallback;

        Gson gson = new GsonBuilder().setLenient().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherbit.io/v2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        WeatherBitService service = retrofit.create(WeatherBitService.class);

        Call<WeatherBCurrentList> call = service.getWeatherCurrent(
                lat,
                lon,
                "ffa8a21ff13949ea9a322e808b35f57f",
                Locale.getDefault().getLanguage()
        );

        Call<WeatherBHourlyList> callHourly = service.getWeatherHourly(
                lat,
                lon,
                "ffa8a21ff13949ea9a322e808b35f57f",
                Locale.getDefault().getLanguage(),
                24
        );

        Call<WeatherBDailyList> callDaily = service.getWeatherDaily(
                lat,
                lon,
                "ffa8a21ff13949ea9a322e808b35f57f",
                Locale.getDefault().getLanguage(),
                7
        );

        call.enqueue(new Callback<WeatherBCurrentList>() {
            @Override
            public void onResponse(Call<WeatherBCurrentList> call, Response<WeatherBCurrentList> response) {
                if (response.isSuccessful()) {
                    weatherBCurrentList = response.body();
                    x++;
                    if (x == 3) {
                        weatherB = new WeatherB(weatherBCurrentList, weatherBHourlyList, weatherBDailyList);
                        wbRepositoryCallback.onSuccess(weatherB);
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherBCurrentList> call, Throwable t) {

            }
        });

        callHourly.enqueue(new Callback<WeatherBHourlyList>() {
            @Override
            public void onResponse(Call<WeatherBHourlyList> call, Response<WeatherBHourlyList> response) {
                weatherBHourlyList = response.body();
                x++;
                if (x == 3) {
                    weatherB = new WeatherB(weatherBCurrentList, weatherBHourlyList, weatherBDailyList);
                    wbRepositoryCallback.onSuccess(weatherB);


                }
            }

            @Override
            public void onFailure(Call<WeatherBHourlyList> call, Throwable t) {
            }
        });

        callDaily.enqueue(new Callback<WeatherBDailyList>() {
            @Override
            public void onResponse(Call<WeatherBDailyList> call, Response<WeatherBDailyList> response) {
                weatherBDailyList = response.body();
                x++;
                if (x == 3) {
                    weatherB = new WeatherB(weatherBCurrentList, weatherBHourlyList, weatherBDailyList);
                    wbRepositoryCallback.onSuccess(weatherB);

                }
            }

            @Override
            public void onFailure(Call<WeatherBDailyList> call, Throwable t) {

            }
        });

    }
}
