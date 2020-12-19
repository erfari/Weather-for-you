package com.example.weatherforyou.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.weatherforyou.repository.CityRepository;
import com.example.weatherforyou.utils.permission.PermissionManager;


public class PagerViewModel {


    private CityRepository cityRepository;

    private MutableLiveData<Boolean> onDataReady;

    private PermissionManager permissionManager;

    private MediatorLiveData<Integer> liveFavoriteCityCount;


    public PagerViewModel(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        liveFavoriteCityCount = new MediatorLiveData<>();

        onDataReady = new MutableLiveData<>();

        liveFavoriteCityCount.addSource(cityRepository.getCityCount(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer cityCount) {
                Log.e("setValue", "count" + cityCount);
                liveFavoriteCityCount.setValue(cityCount);

                onDataReady.setValue(true);
            }
        });
    }

    public void onViewReady() {
        // onDataReady.postValue(false);





        cityRepository.calculateCityCount();
    }

    public LiveData<Integer> getLiveFavoriteCityCount() {
        return liveFavoriteCityCount;
    }

    public LiveData<Boolean> isDataReady() {
        return onDataReady;
    }
}
