package com.example.weatherforyou.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherforyou.JSON.WeatherBit.CurrentB;
import com.example.weatherforyou.JSON.WeatherBit.DailyB;
import com.example.weatherforyou.JSON.WeatherBit.HourlyB;
import com.example.weatherforyou.JSON.WeatherBit.WeatherB;
import com.example.weatherforyou.data.network.WeatherBitController;
import com.example.weatherforyou.model.city.City;
import com.example.weatherforyou.model.weatherapi.DailyResponse;
import com.example.weatherforyou.model.weatherapi.ForecastResponse;
import com.example.weatherforyou.model.weatherapi.HourlyResponse;
import com.example.weatherforyou.model.weatherapi.WeatherService;

import java.util.ArrayList;
import java.util.List;

public class WeatherBitRepository implements WBRepositoryCallback, WeatherService {

    private MutableLiveData<WeatherB> weatherMutableLiveData = new MutableLiveData();

    private MutableLiveData<ForecastResponse> responseMutableLiveData = new MutableLiveData();

    private ForecastResponse weatherForPushB;


    @Override
    public void onSuccess(WeatherB weatherB) {
        weatherForPushB = map(weatherB);
        weatherMutableLiveData.postValue(weatherB);
        responseMutableLiveData.postValue(map(weatherB));
    }


    @Override
    public void onFailure() {

    }

    public ForecastResponse getWeatherForPushB() {
        return weatherForPushB;
    }

    public void setWeatherForPushB(ForecastResponse weatherForPushB) {
        this.weatherForPushB = weatherForPushB;
    }


    public MutableLiveData<WeatherB> getWeatherMutableLiveData() {
        return weatherMutableLiveData;
    }

    @Override
    public LiveData<ForecastResponse> getResponse() {
        return responseMutableLiveData;
    }

    @Override
    public void update(City city) throws InterruptedException {
        new WeatherBitController().start(this, city.getLat(),city.getLon());
    }

    public ForecastResponse map(WeatherB weather) {

        CurrentB current = weather.getWeatherBCurrentList().getCurrentB().get(0);


        return new ForecastResponse() {
            @Override
            public String getSource() {
                return "bit";
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
                return current.getTemperature();
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
                if (current.getPrecipitation() != 0){
                    return current.getPrecipitation();
                } else if (current.getPrecipitationSnow() != 0) {
                    return current.getPrecipitationSnow();
                } else {
                    return 0.0;
                }
            }

            @Override
            public Integer getPressure() {
                return (int)(current.getPressure().doubleValue());
            }

            @Override
            public Integer getHumidity() {
                return (int)current.getHumidity().doubleValue();
            }

            @Override
            public Double getWindSpeed() {
                return current.getWindSpeed();
            }

            @Override
            public Double getWindDeg() {
                return current.getWindSpeed();
            }

            @Override
            public Integer getVisibility() {
                return 1000;
            }

            @Override
            public List<DailyResponse> getDailyResponse() {
                ArrayList<DailyResponse> dailyResponses = new ArrayList<>();

                for (DailyB daily : weather.getWeatherBDailyList().getDailyB()) {
                    DailyResponse response = new DailyResponse() {
                        @Override
                        public int getCity() {
                            return 20;
                        }

                        @Override
                        public int getTime() {
                            return daily.getDtDaily();
                        }

                        @Override
                        public Double getMinTemp() {
                            return daily.getMinTemp();
                        }

                        @Override
                        public Double getMaxTemp() {
                            return daily.getMaxTemp();
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

                for (HourlyB hourly : weather.getWeatherBHourlyList().getTs()){
                    HourlyResponse response = new HourlyResponse() {
                        @Override
                        public int getCity() {
                            return 20;
                        }

                        @Override
                        public int getTime() {
                            return hourly.getDtHourly();
                        }

                        @Override
                        public Double getTemp() {
                            return hourly.getTemperature();
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
