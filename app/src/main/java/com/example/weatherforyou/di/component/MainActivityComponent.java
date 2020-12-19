package com.example.weatherforyou.di.component;

import com.example.weatherforyou.di.module.ForecastModule;
import com.example.weatherforyou.di.scope.ActivityScope;
import com.example.weatherforyou.di.module.MainActivityModule;
import com.example.weatherforyou.ui.MainActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {MainActivityModule.class})
public interface MainActivityComponent {

    void inject(MainActivity activity);
    ForecastComponent plus(ForecastModule forecastModule);
}
