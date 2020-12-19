package com.example.weatherforyou.model.weatherapi;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.weatherforyou.data.db.entities.DailyEntity;
import com.example.weatherforyou.data.db.entities.HourlyEntity;
import com.example.weatherforyou.data.db.entities.InfoEntity;
import com.example.weatherforyou.data.db.entities.WeatherEntity;
import com.example.weatherforyou.model.city.City;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * в этом классе происходит магия получения прогноза погоды;
 */
public class WeatherApi {

    //список сервисов, где мы получаем данные о погоде
    private ArrayList<WeatherService> weatherServices = new ArrayList<>();

    //уснонавливется в true если пришли данные из всех сервисов
    private MediatorLiveData<ForecastResponse> isUpdate = new MediatorLiveData<>();

    //собстенно список ответов от сервисов погоды
    private ArrayList<ForecastResponse> forecastResponses = new ArrayList<>();

    public ArrayList<ForecastResponse> getForecastResponses() {
        return forecastResponses;
    }

    //счетчик количество ответов сервисов
    private int update = 0;

    //текущий город
    private City city;


    //фунция добавляет сервис прогноза погоды
    public boolean addService(WeatherService weatherService) {

        if (weatherService != null) {

            try {

                isUpdate.addSource(weatherService.getResponse(), new Observer<ForecastResponse>() {
                    @Override
                    public void onChanged(ForecastResponse response) {
                        forecastResponses.add(response);
                        update++;
                        if (update == weatherServices.size()) {
                            isUpdate.postValue(getMean(forecastResponses));
                        }

                    }
                });
                weatherServices.add(weatherService);
            } catch (IllegalArgumentException e) {

            }

            return true;
        }

        return false;

    }


    public LiveData<ForecastResponse> getForResponse() {
        return isUpdate;
    }

    //создает в новом потоке запрос к погодным сервисам для обновления прогноза
    public void updateCurrentWeather(City city) {
        this.city = city;
        for (WeatherService weatherService : weatherServices) {

            new Thread(() -> {
                try {
                    weatherService.update(city);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ).start();

        }

    }

    //преобразует тип данных сервисов в тип данных подходящий для сохранения в базу данных
    public WeatherEntity mapToDatabaseModel(ForecastResponse response) {

        ArrayList<DailyEntity> dailyEntities = new ArrayList<>();
        for (DailyResponse dailyResponse : response.getDailyResponse()) {
            dailyEntities.add(new DailyEntity(dailyResponse.getCity(), dailyResponse.getTime(), dailyResponse.getMinTemp(), dailyResponse.getMaxTemp(), dailyResponse.getIconWithUrl()));
        }

        ArrayList<HourlyEntity> hourlyEntities = new ArrayList<>();
        for (HourlyResponse hourlyResponse : response.getHourlyResponse()) {
            hourlyEntities.add(new HourlyEntity(hourlyResponse.getCity(), hourlyResponse.getTime(), hourlyResponse.getTemp(), hourlyResponse.getIconWithUrl()));
        }

        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        WeatherEntity entity = new WeatherEntity(city.getLat()
                , city.getLon()
                , city.getName()
                , response.getDescription()
                , response.getTemp()
                , response.getTimeUpdate()
                , response.getIconId()
                , new InfoEntity(response.getHumidity().toString()
                , decimalFormat.format(response.getVisibility())
                , decimalFormat.format(response.getWindSpeed())
                , decimalFormat.format(response.getWindDeg())
                , decimalFormat.format(response.getPrecipitation())
                , response.getPressure().toString()
                , response.getSunRise()
                , response.getSunSet())
                , dailyEntities
                , hourlyEntities);

        return entity;
    }

    public WeatherEntity mapToDatabaseModelByPush(ForecastResponse response, City city) {

        ArrayList<DailyEntity> dailyEntities = new ArrayList<>();
        for (DailyResponse dailyResponse : response.getDailyResponse()) {
            dailyEntities.add(new DailyEntity(dailyResponse.getCity(), dailyResponse.getTime(), dailyResponse.getMinTemp(), dailyResponse.getMaxTemp(), dailyResponse.getIconWithUrl()));
        }

        ArrayList<HourlyEntity> hourlyEntities = new ArrayList<>();
        for (HourlyResponse hourlyResponse : response.getHourlyResponse()) {
            hourlyEntities.add(new HourlyEntity(hourlyResponse.getCity(), hourlyResponse.getTime(), hourlyResponse.getTemp(), hourlyResponse.getIconWithUrl()));
        }

        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        WeatherEntity entity = new WeatherEntity(city.getLat()
                , city.getLon()
                , city.getName()
                , response.getDescription()
                , response.getTemp()
                , response.getTimeUpdate()
                , response.getIconId()
                , new InfoEntity(response.getHumidity().toString()
                , decimalFormat.format(response.getVisibility())
                , decimalFormat.format(response.getWindSpeed())
                , decimalFormat.format(response.getWindDeg())
                , decimalFormat.format(response.getPrecipitation())
                , response.getPressure().toString()
                , response.getSunRise()
                , response.getSunSet())
                , dailyEntities
                , hourlyEntities);

        return entity;
    }

    //вычисляет среднее занчение погоды
    public ForecastResponse getMean(List<ForecastResponse> forecastResponses) {

        ForecastResponseImpl result = new ForecastResponseImpl(0
                , ""
                , ""
                , 0.0
                , 0
                , 0
                , 0.0
                , 0
                , 0
                , 0.0
                , 0.0
                , 0
                , null
                , null);

        List<DailyResponseImpl> dailyResult = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dailyResult.add(new DailyResponseImpl());
        }

        List<HourlyResponseImpl> hourlyResult = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            hourlyResult.add(new HourlyResponseImpl());
        }


        for (ForecastResponse forecastResponse : forecastResponses) {
            //поля, для которых сщитать средее избытачно, берем из сервиса OpenWeather
            //так как сервис предоставляет все необходимые данные
            if (forecastResponse.getSource().equals("openWeather")) {
                result.setTimeUpdate(forecastResponse.getTimeUpdate());
                result.setDescription(forecastResponse.getDescription());
                result.setIconId(forecastResponse.getIconId());
                result.setSunRise(forecastResponse.getSunRise());
                result.setHumidity(forecastResponse.getHumidity());
                result.setSunSet(forecastResponse.getSunSet());

                for (int i = 0; i < 7; i++) {
                    dailyResult.get(i).addInfo(forecastResponse.getDailyResponse().get(i));
                }

                for (int i = 0; i < 24; i++) {
                    hourlyResult.get(i).addInfo(forecastResponse.getHourlyResponse().get(i));
                }

            }
            // магия сложения
            result.setTemp(forecastResponse.getTemp() + result.getTemp());
            result.setPressure(forecastResponse.getPressure() + result.getPressure());
            result.setWindSpeed(forecastResponse.getWindSpeed() + result.getWindSpeed());
            result.setWindDeg(forecastResponse.getWindDeg() + result.getWindDeg());
            result.setVisibility(forecastResponse.getVisibility() + result.getVisibility());
            result.setPrecipitation(forecastResponse.getPrecipitation() + result.getPrecipitation());

            for (int i = 0; i < 7; i++) {
                dailyResult.get(i).plus(forecastResponse.getDailyResponse().get(i));
            }

            for (int i = 0; i < 24; i++) {
                hourlyResult.get(i).plus(forecastResponse.getHourlyResponse().get(i));
            }
        }

        //магия деления
        result.setTemp(result.getTemp() / forecastResponses.size());
        result.setPressure(result.getPressure() / forecastResponses.size());
        result.setHumidity(result.getHumidity() / forecastResponses.size());
        result.setWindSpeed(result.getWindSpeed() / forecastResponses.size());
        result.setWindDeg(result.getWindDeg() / forecastResponses.size());
        result.setVisibility(result.getVisibility() / forecastResponses.size());
        result.setPrecipitation(result.getPrecipitation() / forecastResponses.size());

        for (int i = 0; i < 7; i++) {
            dailyResult.get(i).div(forecastResponses.size());
        }

        for (int i = 0; i < 24; i++) {
            hourlyResult.get(i).div(forecastResponses.size());
        }


        //магия результата
        result.setDailyResponses(dailyResult);
        result.setHourlyResponses(hourlyResult);

        return result;

    }
}
