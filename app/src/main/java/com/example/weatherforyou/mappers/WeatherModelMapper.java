package com.example.weatherforyou.mappers;

import android.content.Context;

import com.example.weatherforyou.App;
import com.example.weatherforyou.R;
import com.example.weatherforyou.data.db.entities.DailyEntity;
import com.example.weatherforyou.data.db.entities.HourlyEntity;
import com.example.weatherforyou.data.db.entities.WeatherEntity;
import com.example.weatherforyou.model.weather.DailyWeather;
import com.example.weatherforyou.model.weather.DailyWeatherImpl;
import com.example.weatherforyou.model.weather.HourWeather;
import com.example.weatherforyou.model.weather.HourWeatherImpl;
import com.example.weatherforyou.model.weather.WeatherInfo;
import com.example.weatherforyou.model.weather.WeatherInfoImpl;
import com.example.weatherforyou.model.weather.WeatherInfoUIModel;
import com.example.weatherforyou.model.weather.WeatherInfoUiModelImpl;
import com.example.weatherforyou.model.weather.WeatherModel;
import com.example.weatherforyou.model.weather.WeatherModelImpl;
import com.example.weatherforyou.ui.MainActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeatherModelMapper {

    Context context;

    public WeatherModelMapper(Context context) {
        this.context = context;
    }

    public WeatherModel toWeatherModel(WeatherEntity weatherEntity) {

        WeatherModel weatherModel;

        Double lat = weatherEntity.getLat();
        Double lon = weatherEntity.getLon();
        String cityName = "Moscow";
        String description = weatherEntity.getDescription();
        String iconId = weatherEntity.getIconId();
        String temperature = new DecimalFormat("###").format(weatherEntity.getTemperature()) + "\u00B0";
        int lastUpdateTime = weatherEntity.getLastUpdateTime();
        WeatherInfo modelInfo = new WeatherInfoImpl(
                "0",
                weatherEntity.getInfoEntity().getHumidity(),
                weatherEntity.getInfoEntity().getVisibility(),
                weatherEntity.getInfoEntity().getWindSpeed(),
                weatherEntity.getInfoEntity().getWindDirection(),
                weatherEntity.getInfoEntity().getPrecipitation(),
                weatherEntity.getInfoEntity().getPressure(),
                String.valueOf(weatherEntity.getInfoEntity().getSunrise()),
                String.valueOf(weatherEntity.getInfoEntity().getSunset())
        );

        ArrayList<WeatherInfoUIModel> weatherInfoUIModels = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        String s = context.getResources().getString(R.string.humidity);
        String[] weatherParameters = {
                context.getResources().getString(R.string.humidity),
                context.getResources().getString(R.string.visibility),
                context.getResources().getString(R.string.wind_speed),
                context.getResources().getString(R.string.wind_direction),
                context.getResources().getString(R.string.precipitation),
                context.getResources().getString(R.string.pressure),
                context.getResources().getString(R.string.sunrise),
                context.getResources().getString(R.string.sunset),
        };


        weatherInfoUIModels.add(new WeatherInfoUiModelImpl(weatherParameters[0], weatherEntity.getInfoEntity().getHumidity()));
        weatherInfoUIModels.add(new WeatherInfoUiModelImpl(weatherParameters[1], weatherEntity.getInfoEntity().getVisibility()));
        weatherInfoUIModels.add(new WeatherInfoUiModelImpl(weatherParameters[2], weatherEntity.getInfoEntity().getWindSpeed()));
        weatherInfoUIModels.add(new WeatherInfoUiModelImpl(weatherParameters[3], weatherEntity.getInfoEntity().getWindDirection()));
        weatherInfoUIModels.add(new WeatherInfoUiModelImpl(weatherParameters[4], weatherEntity.getInfoEntity().getPrecipitation()));
        weatherInfoUIModels.add(new WeatherInfoUiModelImpl(weatherParameters[5], weatherEntity.getInfoEntity().getPressure()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date sunrise = new Date( (long) weatherEntity.getInfoEntity().getSunrise() * 1000);
        weatherInfoUIModels.add(new WeatherInfoUiModelImpl(weatherParameters[6], sdf.format(sunrise)));
        Date sunset = new Date( (long) weatherEntity.getInfoEntity().getSunset() * 1000);
        weatherInfoUIModels.add(new WeatherInfoUiModelImpl(weatherParameters[7], sdf.format(sunset)));

        List<HourWeather> hourWeatherList = new ArrayList<>();
        List<DailyWeather> dailyWeatherList = new ArrayList<>();


        for (DailyEntity dailyEntity : weatherEntity.getDailyWeather()) {
            SimpleDateFormat sdfDaily = new SimpleDateFormat("E, dd MMM");
            Date dateDaily = new Date((long) dailyEntity.getTime() * 1000);
            dailyWeatherList.add(new DailyWeatherImpl(
                    sdfDaily.format(dateDaily),
                    new DecimalFormat("###").format(dailyEntity.getMinTemp()) + "\u00B0",
                    new DecimalFormat("###").format(dailyEntity.getMaxTemp()) + "\u00B0",
                    dailyEntity.getIconIdWithUrl()));
        }

        for (HourlyEntity hourlyEntity : weatherEntity.getHourlyWeather()) {
            SimpleDateFormat sdfHourly = new SimpleDateFormat("HH:mm");
            Date date = new Date((long) hourlyEntity.getTime() * 1000);
            hourWeatherList.add(new HourWeatherImpl(
                    sdfHourly.format(date),
                    new DecimalFormat("###").format(hourlyEntity.getTemperature()) + "\u00B0",
                    hourlyEntity.getIconIdWithUrl()));
        }

        weatherModel = new WeatherModelImpl(lat, lon, cityName, lastUpdateTime, temperature, description, iconId, modelInfo, hourWeatherList, dailyWeatherList, weatherInfoUIModels);


        return weatherModel;
    }
}
