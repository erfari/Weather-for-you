package com.example.weatherforyou.di.module;

import android.content.Context;
import android.location.LocationManager;

import com.example.weatherforyou.di.scope.ActivityScope;
import com.example.weatherforyou.di.scope.FunctionScope;
import com.example.weatherforyou.model.city.City;
import com.example.weatherforyou.repository.CityRepository;
import com.example.weatherforyou.utils.location.LiveLocationUpdater;
import com.example.weatherforyou.utils.permission.PermissionManager;
import com.example.weatherforyou.viewmodel.PagerViewModel;
import com.example.weatherforyou.viewmodel.WelcomeViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ForecastModule {


    @FunctionScope
    @Provides
    public CityRepository provideCityRepository(Context context){
        return new CityRepository(context);
    }

    @FunctionScope
    @Provides
    public PagerViewModel providePagerViewModel(CityRepository repository){
        return new PagerViewModel(repository);
    }

    @Provides
    public LiveLocationUpdater provideLocationUpdater(LocationManager locationManager){
        return new LiveLocationUpdater(locationManager);
    }

    @FunctionScope
    @Provides
    public WelcomeViewModel provideWelcomeViewModel(PermissionManager permissionManager, CityRepository repository, LiveLocationUpdater liveLocationUpdater, Context context){
        return new WelcomeViewModel(permissionManager, liveLocationUpdater, repository, context);
    }
}
