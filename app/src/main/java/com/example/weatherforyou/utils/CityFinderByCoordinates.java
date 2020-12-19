package com.example.weatherforyou.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CityFinderByCoordinates {



    public static String getCityName(Context context, double lat, double lon){
        List<Address> addresses = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
             addresses = geocoder.getFromLocation(lat,lon,1);
            Log.e("city", addresses.get(0).getLocality());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return addresses.get(0).getLocality();
    }
}
