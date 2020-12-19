package com.example.weatherforyou.di.component;


import android.content.Context;

import com.example.weatherforyou.di.module.ApplicationModule;
import com.example.weatherforyou.di.module.MainActivityModule;
import com.example.weatherforyou.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    MainActivityComponent plus(MainActivityModule mainActivityModule);
    Context getContext();
}
