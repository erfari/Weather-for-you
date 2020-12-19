package com.example.weatherforyou.repository;

import com.example.weatherforyou.JSON.WeatherBit.WeatherB;
import com.example.weatherforyou.JSON.Yandex.WeatherY;

public interface YaRepositoryCallback {

        void onSuccess(WeatherY weatherY);

        void onFailure();
}
