package com.example.weatherforyou.utils.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherforyou.App;
import com.example.weatherforyou.R;

public class LiveLocationUpdater implements LocationListener {

    private MutableLiveData<Location> liveLocation;

    private MutableLiveData<Boolean> isDataSourceAvailable;

    private LocationManager locationManager;

    public LiveLocationUpdater(LocationManager locationManager) {
        this.locationManager = locationManager;
        liveLocation = new MutableLiveData<>();
        isDataSourceAvailable = new MutableLiveData<>();
    }

    public LiveData<Boolean> getIsDataSourceAvailable() {
        return isDataSourceAvailable;
    }

    @SuppressLint("MissingPermission")
    public void startGetLocation(){
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            isDataSourceAvailable.setValue(true);
        }else {
            isDataSourceAvailable.setValue(false);
        }


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000*10,10, this);

    }


    public void stopGetLocation(){
        locationManager.removeUpdates(this);
    }

    public LiveData<Location> getLiveLocation(){
        return liveLocation;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        Log.e("setValue", "location change");
        liveLocation.postValue(location);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Context  context = App.AppInstance.getInstance().getApplicationContext();
        Toast.makeText(context, "gps enable", Toast.LENGTH_LONG).show();
        isDataSourceAvailable.setValue(true);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Context  context = App.AppInstance.getInstance().getApplicationContext();
        isDataSourceAvailable.setValue(false);
        Toast.makeText(context, R.string.gps_enable, Toast.LENGTH_LONG).show();
    }


}
