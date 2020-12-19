package com.example.weatherforyou.di.module;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;

import com.example.weatherforyou.di.scope.ActivityScope;
import com.example.weatherforyou.repository.CityRepository;
import com.example.weatherforyou.utils.permission.PermissionManager;
import com.example.weatherforyou.viewmodel.PagerViewModel;
import com.example.weatherforyou.viewmodel.PermissionViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    Activity activity;

    public MainActivityModule(Activity activity) {
        this.activity = activity;
    }


    @ActivityScope
    @Provides
    public LocationManager provideLocationManager(){
        return (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
    }

    @ActivityScope
    @Provides
    public PermissionManager providePermissionManager(){
        return new PermissionManager(activity);
    }

    @ActivityScope
    @Provides
    public PermissionViewModel providePermissionViewModel(){
        return  PermissionViewModel.getInstance();
    }
}
