package com.example.weatherforyou.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherforyou.JSON.Yandex.CurrentY;
import com.example.weatherforyou.JSON.Yandex.DailyY;
import com.example.weatherforyou.JSON.Yandex.HourlyTempY;
import com.example.weatherforyou.JSON.Yandex.WeatherY;
import com.example.weatherforyou.data.network.YandexWeatherControler;
import com.example.weatherforyou.model.city.City;
import com.example.weatherforyou.model.weatherapi.DailyResponse;
import com.example.weatherforyou.model.weatherapi.ForecastResponse;
import com.example.weatherforyou.model.weatherapi.HourlyResponse;
import com.example.weatherforyou.model.weatherapi.WeatherService;

import java.util.ArrayList;
import java.util.List;

public class YaWeatherRepository implements YaRepositoryCallback, WeatherService {

    private MutableLiveData<WeatherY> weatherMutableLiveData = new MutableLiveData();

    private MutableLiveData<ForecastResponse> responseMutableLiveData = new MutableLiveData();


    private ForecastResponse weatherForPushY;



    @Override
    public void onSuccess(WeatherY weatherY) {
        weatherForPushY = map(weatherY);

        weatherMutableLiveData.postValue(weatherY);
        responseMutableLiveData.postValue(map(weatherY));
    }


    @Override
    public void onFailure() {

    }

    public ForecastResponse getWeatherForPushY() {
        return weatherForPushY;
    }

    public void setWeatherForPushY(ForecastResponse weatherForPushY) {
        this.weatherForPushY = weatherForPushY;
    }


    @Override
    public LiveData<ForecastResponse> getResponse() {
        return responseMutableLiveData;
    }

    @Override
    public void update(City city) throws InterruptedException {
        new YandexWeatherControler().start(this, city.getLat(), city.getLon());
    }

    private ForecastResponse map(WeatherY weatherY) {
        CurrentY currentY = weatherY.getCurrentY();
        List<HourlyTempY> hourlyTempYList = weatherY.getDailyY().get(0).getHourlyTempY();
        List<DailyY> dailyTempYList = weatherY.getDailyY();

        return new ForecastResponse(
        ) {
            @Override
            public String getSource() {
                return "Yandex";
            }

            @Override
            public Integer getTimeUpdate() {
                return null;
            }

            @Override
            public String getIconId() {
                return null;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public Double getTemp() {
                return currentY.getTemperature();
            }

            @Override
            public Integer getSunRise() {
                return 0;
            }

            @Override
            public Integer getSunSet() {
                return 0;
            }

            @Override
            public Double getPrecipitation() {
                return currentY.getPrecipitation();
            }

            @Override
            public Integer getPressure() {
                return (int) currentY.getPressure().doubleValue();
            }

            @Override
            public Integer getHumidity() {
                return (int) currentY.getHumidity().doubleValue();
            }

            @Override
            public Double getWindSpeed() {
                return currentY.getWindSpeed();
            }

            @Override
            public Double getWindDeg() {
                return currentY.getWindSpeed();
            }

            @Override
            public Integer getVisibility() {
                return 1000;
            }

            @Override
            public List<DailyResponse> getDailyResponse() {
                ArrayList<DailyResponse> dailyResponses = new ArrayList<>();

                for (DailyY dailyY : dailyTempYList) {
                    DailyResponse response = new DailyResponse() {
                        @Override
                        public int getCity() {
                            return 20;
                        }

                        @Override
                        public int getTime() {
                            return dailyY.getDtDay();
                        }

                        @Override
                        public Double getMinTemp() {
                            return dailyY.getMinMaxTemp().getNightTemp().getMinTemp();
                        }

                        @Override
                        public Double getMaxTemp() {
                            return dailyY.getMinMaxTemp().getDayTemp().getMaxTemp();
                        }

                        @Override
                        public String getIconWithUrl() {
                            return "icon";
                        }
                    };
                    dailyResponses.add(response);
                }

                return dailyResponses;
            }

            @Override
            public List<HourlyResponse> getHourlyResponse() {

                ArrayList<HourlyResponse> hourlyResponses = new ArrayList<>();

                for (HourlyTempY hourlyTempY : hourlyTempYList) {
                    HourlyResponse response = new HourlyResponse() {
                        @Override
                        public int getCity() {
                            return 20;
                        }

                        @Override
                        public int getTime() {
                            return hourlyTempY.getDtHour();
                        }

                        @Override
                        public Double getTemp() {
                            return hourlyTempY.getTempHour();
                        }

                        @Override
                        public String getIconWithUrl() {
                            return "icon";
                        }
                    };
                    hourlyResponses.add(response);
                }
                return hourlyResponses;
            }
        };
    }

}
