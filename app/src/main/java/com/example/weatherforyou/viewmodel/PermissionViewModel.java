package com.example.weatherforyou.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PermissionViewModel {

    private static PermissionViewModel instance;

    public MutableLiveData<Boolean> isLocationPermissionGranted = new MutableLiveData<>();

    public boolean isPermanentlyDenied = false;

    private PermissionViewModel() {

    }

    public static PermissionViewModel getInstance(){
        if (instance == null){
            instance = new PermissionViewModel();
        }

        return instance;
    }

    public LiveData<Boolean> getIsLocationPermissionGranted() {
        return isLocationPermissionGranted;
    }

    public void changePermission(boolean isGranted){
        isLocationPermissionGranted.postValue(isGranted);
    }
}
