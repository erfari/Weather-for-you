package com.example.weatherforyou.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherforyou.App;
import com.example.weatherforyou.model.city.CityImpl;
import com.example.weatherforyou.model.city.FavoriteCity;
import com.example.weatherforyou.repository.CityRepository;
import com.example.weatherforyou.ui.adapter.listeners.ItemClickListener;

import java.util.List;

public class FavoriteCityViewModel extends ViewModel implements ItemClickListener<FavoriteCity> {

    private MutableLiveData<List<FavoriteCity>> liveFavoriteCity = new MutableLiveData<>();

    private MutableLiveData<FavoriteCity> clickedCity = new MutableLiveData<>();

    private CityRepository cityRepository = new CityRepository(App.AppInstance.getInstance().getApplicationContext());

    public LiveData<List<FavoriteCity>> getLiveFavoriteCity() {
        return liveFavoriteCity;
    }

    public MutableLiveData<FavoriteCity> getClickedCity() {
        return clickedCity;
    }

    @Override
    public void onClick(FavoriteCity item) {
        clickedCity.postValue(item);
    }

    public void deleteCity(FavoriteCity favoriteCity){
        cityRepository.delete(new CityImpl(favoriteCity.getCityName(),0.0,0.0));
        updateCityList();
    }

    public void updateCityList(){
        List<FavoriteCity> result = cityRepository.getFavoriteCities();
        liveFavoriteCity.postValue(result);
    }
}
