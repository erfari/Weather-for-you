package com.example.weatherforyou;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.work.PeriodicWorkRequest;

import com.example.weatherforyou.data.db.WeatherDatabase;
import com.example.weatherforyou.di.component.ApplicationComponent;
import com.example.weatherforyou.di.component.DaggerApplicationComponent;
import com.example.weatherforyou.di.component.ForecastComponent;
import com.example.weatherforyou.di.component.MainActivityComponent;
import com.example.weatherforyou.di.component.PagerFragmentComponent;
import com.example.weatherforyou.di.module.ApplicationModule;
import com.example.weatherforyou.di.module.ForecastModule;
import com.example.weatherforyou.di.module.MainActivityModule;
import com.example.weatherforyou.di.module.PagerFragmentModule;
import com.example.weatherforyou.ui.PushWorker;
import com.google.android.libraries.places.api.Places;




import java.util.concurrent.TimeUnit;


/**
 * Класс Application - это базовый класс приложения android. При запуске программы вначале создается экземпляр этого класса, а потом уже необходимые Activity
 *
 *
 * в приложении используется для инициализации Dagger компонент
 */
public class App extends Application {

    WeatherDatabase database;

    private ApplicationComponent applicationComponent;

    private MainActivityComponent mainActivityComponent;

    private ForecastComponent forecastComponent;

    private PagerFragmentComponent pagerFragmentComponent;

    public WeatherDatabase getDatabase() {
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(getApplicationContext(), WeatherDatabase.class, "weatherdatabase").allowMainThreadQueries().build();


        //инициализация dagger 2
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        //инициализируем Api списка городов
        if (!Places.isInitialized()) {
            Places.initialize(this.getApplicationContext(), "AIzaSyCs_3xUDy1n-m6UKp47vvpKflxtWqpHVY4");
        }

        AppInstance.setInstance(this);
    }

    public MainActivityComponent getMainActivityComponent(Activity activity) {

        if(mainActivityComponent == null){
            mainActivityComponent = applicationComponent.plus( new MainActivityModule(activity));
        }
        return mainActivityComponent;
    }


    public ForecastComponent getForecastComponent() {

        if (forecastComponent == null){
            forecastComponent = mainActivityComponent.plus(new ForecastModule());
        }
        return forecastComponent;
    }



    public PagerFragmentComponent getPagerFragmentComponent() {
        if (pagerFragmentComponent == null){
            pagerFragmentComponent = forecastComponent.plus(new PagerFragmentModule());
        }

        return pagerFragmentComponent;
    }


    //получаем объек класса App из любой точки приложения
    public static class AppInstance {
        private static App mApp;

        public static void setInstance(App app) {
            mApp = app;
        }

        public static App getInstance() {
            return mApp;
        }

    }
}


