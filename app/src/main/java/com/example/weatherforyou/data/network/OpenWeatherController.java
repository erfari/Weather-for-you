package com.example.weatherforyou.data.network;

import android.util.Log;

import com.example.weatherforyou.JSON.OpenWeather.Weather;
import com.example.weatherforyou.repository.RepositoryCallback;
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

public class OpenWeatherController implements Callback<Weather> {
    static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    RepositoryCallback repositoryCallback;

    public void start(RepositoryCallback repositoryCallback, double lat, double lon) {
        Gson gson = new GsonBuilder().setLenient().create();

        this.repositoryCallback = repositoryCallback;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        OpenWeatherService service = retrofit.create(OpenWeatherService.class);

        Call<Weather> call = service.getWeather(lat,
                lon,
                    "c2b8a9ca1d4763ce4308de9c3ebcc05d",
                "metric",
                Locale.getDefault().getLanguage(),
                "minutely");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Weather> call, Response<Weather> response) {
        if (response.isSuccessful()) {
            Weather weather = response.body();
            repositoryCallback.onSuccess(weather);
        }

    }

    @Override
    public void onFailure(Call<Weather> call, Throwable t) {
        t.printStackTrace();
    }
}
