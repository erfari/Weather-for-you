package com.example.weatherforyou.viewmodel;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.example.weatherforyou.R;
import com.example.weatherforyou.data.db.dao.CityDao;
import com.example.weatherforyou.data.db.entities.CityEntity;
import com.example.weatherforyou.repository.CityRepository;
import com.example.weatherforyou.utils.CityFinderByCoordinates;
import com.example.weatherforyou.utils.location.LiveLocationUpdater;
import com.example.weatherforyou.utils.permission.PermissionManager;

public class WelcomeViewModel {

    private PermissionManager locationPermissionManager;

    private LiveLocationUpdater locationUpdater;

    private CityRepository cityRepository;

    private Context context;

    private MutableLiveData<Boolean> navigateUp;

    private MutableLiveData<Boolean> isShowProgressBar;

    public WelcomeViewModel(PermissionManager locationPermissionManager, LiveLocationUpdater locationUpdater, CityRepository cityRepository, Context context) {
        this.locationPermissionManager = locationPermissionManager;
        this.locationUpdater = locationUpdater;
        this.cityRepository = cityRepository;
        this.context = context;

        navigateUp = new MutableLiveData<>();
        isShowProgressBar = new MutableLiveData<>();
    }

    public LiveData<Boolean> getNavigateUp() {
        return navigateUp;
    }

    public void loadCityFromMyLocation() {
        getMyLocation();
    }

    public MutableLiveData<Boolean> getIsShowProgressBar() {
        return isShowProgressBar;
    }

    private void getMyLocation() {
        if (locationPermissionManager.isPermissionGranted()){
          updateLocation();
        }else {
            locationPermissionManager.requestPermission();
        }

    }

    private void updateLocation(){
        locationUpdater.startGetLocation();
        locationUpdater.getIsDataSourceAvailable().observeForever(new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Log.e("available", aBoolean.toString());
                    navigateUp.setValue(false);
                    isShowProgressBar.setValue(true);
                    locationUpdater.getLiveLocation().observeForever(new Observer<Location>() {
                        @Override
                        public void onChanged(Location location) {
                            String cityName = CityFinderByCoordinates.getCityName(context, location.getLatitude(), location.getLongitude());

                            Log.e("city name", cityName);
                            cityRepository.saveCity(cityName, location);
                            locationUpdater.stopGetLocation();
                            isShowProgressBar.setValue(false);
                            navigateUp.setValue(true);
                        }
                    });
                }
            }
        });


    }


}
