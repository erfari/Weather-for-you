package com.example.weatherforyou.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.weatherforyou.App;
import com.example.weatherforyou.data.db.entities.WeatherEntity;
import com.example.weatherforyou.model.city.City;
import com.example.weatherforyou.model.weatherapi.ForecastResponse;
import com.example.weatherforyou.model.weatherapi.WeatherApi;
import com.example.weatherforyou.repository.CityRepository;
import com.example.weatherforyou.repository.OpenWeatherRepository;
import com.example.weatherforyou.repository.WeatherBitRepository;
import com.example.weatherforyou.repository.YaWeatherRepository;


public class WeatherViewModel extends ViewModel {

    YaWeatherRepository yaWeatherRepository = new YaWeatherRepository();

    WeatherBitRepository weatherBitRepository = new WeatherBitRepository();

    OpenWeatherRepository repository = new OpenWeatherRepository();

    private CityRepository cityRepository;

    private WeatherApi weatherApi = new WeatherApi();

    private MutableLiveData<City> liveCity = new MutableLiveData<>();


    private LiveData<WeatherEntity> weather = new MutableLiveData<>();

    City city;

    public void init(Context context) {

        cityRepository = new CityRepository(context);

        weatherApi.addService(repository);
        weatherApi.addService(weatherBitRepository);
        weatherApi.addService(yaWeatherRepository);

        weatherApi.getForResponse().observeForever(new Observer<ForecastResponse>() {
            @Override
            public void onChanged(ForecastResponse response) {

                App.AppInstance.getInstance().getDatabase().getWeatherDao().insert(weatherApi.mapToDatabaseModel(response));
            }
        });
    }

    public void update(int cityId) {

        city = cityRepository.getCityBy(cityId);

        weather = repository.getWeatherFromDatabase(city.getName());

        weatherApi.updateCurrentWeather(city);

        liveCity.postValue(cityRepository.getCityBy(cityId));
    }


    public LiveData<City> getCity() {
        return liveCity;
    }

    public LiveData<WeatherEntity> getWeather() {
        return weather;
    }
}