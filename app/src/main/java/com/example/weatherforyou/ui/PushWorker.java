package com.example.weatherforyou.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.weatherforyou.App;
import com.example.weatherforyou.JSON.City;
import com.example.weatherforyou.JSON.OpenWeather.Current;
import com.example.weatherforyou.R;
import com.example.weatherforyou.data.db.entities.WeatherEntity;
import com.example.weatherforyou.model.city.CityImpl;
import com.example.weatherforyou.model.weatherapi.ForecastResponse;
import com.example.weatherforyou.model.weatherapi.ForecastResponseImpl;
import com.example.weatherforyou.model.weatherapi.WeatherApi;
import com.example.weatherforyou.repository.OpenWeatherRepository;
import com.example.weatherforyou.repository.WeatherBitRepository;
import com.example.weatherforyou.repository.YaWeatherRepository;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PushWorker extends Worker {

    private static final String PUSH_ID = "push";

    public PushWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {
        Log.d("MYLOG", "doWork: start");

        boolean updateContent = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("update_content", true);

        //new
        if (!updateContent) {
            updateWeather();
        } else if (checkWifiOnAndConnected()) {
            updateWeather();
        }
        //---


        WeatherEntity currentCity2 = App.AppInstance.getInstance().getDatabase().getWeatherDao().getCurrentCity();

        Log.i("MYLOG", currentCity2.getLastUpdateTime() + " 11 ");

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "push")
                .setSmallIcon(R.drawable.ic_cloud_24)
                .setContentTitle(currentCity2.getCityName() + "   " + sdf.format(currentCity2.getLastUpdateTime()))
                .setContentText(new DecimalFormat("###.#").format(currentCity2.getTemperature()) + "\u00B0C   " + currentCity2.getDescription().split(",")[0])
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManager mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }

        mNotificationManager.notify(0, builder.build());


        return Result.success();
    }

    private void updateWeather() {
        OpenWeatherRepository repository = new OpenWeatherRepository();
        WeatherBitRepository weatherBitRepository = new WeatherBitRepository();
        YaWeatherRepository yaWeatherRepository = new YaWeatherRepository();
        WeatherApi weatherApi = new WeatherApi();
        weatherApi.addService(repository);
        weatherApi.addService(weatherBitRepository);
        weatherApi.addService(yaWeatherRepository);

        WeatherEntity currentCity = App.AppInstance.getInstance().getDatabase().getWeatherDao().getCurrentCity();

        Log.i("MYLOG", String.valueOf(currentCity.getLastUpdateTime()));

        CityImpl city = new CityImpl(currentCity.getCityName(), currentCity.getLat(), currentCity.getLon());
        try {

            repository.update(city);
            weatherBitRepository.update(city);
            yaWeatherRepository.update(city);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            TimeUnit.SECONDS.sleep(10);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<ForecastResponse> forecastResponseList = new ArrayList<ForecastResponse>();

        if (repository.getWeatherforPush() != null || weatherBitRepository.getWeatherForPushB() != null || yaWeatherRepository.getWeatherForPushY() != null) {
            forecastResponseList.add(repository.getWeatherforPush());
            forecastResponseList.add(weatherBitRepository.getWeatherForPushB());
            forecastResponseList.add(yaWeatherRepository.getWeatherForPushY());
        }

        ForecastResponse mean = weatherApi.getMean(forecastResponseList);
        App.AppInstance.getInstance().getDatabase().getWeatherDao().insert(weatherApi.mapToDatabaseModelByPush(mean, city));
    }

    // new
    private boolean checkWifiOnAndConnected() {
        SupplicantState supState;
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        supState = wifiInfo.getSupplicantState();
        if (supState == SupplicantState.COMPLETED) {
            return true;
        } else {
            return false;
        }

    }
    // ---

    @Override
    public void onStopped() {
        Log.d("MYLOG", "doWork: stop");
        super.onStopped();

    }
}
