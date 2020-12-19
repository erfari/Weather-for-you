package com.example.weatherforyou.di.module;


import android.content.Context;

import androidx.room.Room;

import com.example.weatherforyou.App;
import com.example.weatherforyou.data.db.WeatherDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    App application;


    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    public Context provideApplicationContext(){
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public WeatherDatabase provideWeatherDatabase(Context context){
        return  Room.databaseBuilder(context, WeatherDatabase.class, "weatherdatabase")
                .allowMainThreadQueries()
                .build();
    }
}
