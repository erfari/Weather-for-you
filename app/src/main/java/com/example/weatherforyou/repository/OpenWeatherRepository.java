package com.example.weatherforyou.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherforyou.App;
import com.example.weatherforyou.JSON.OpenWeather.Current;
import com.example.weatherforyou.JSON.OpenWeather.Daily;
import com.example.weatherforyou.JSON.OpenWeather.Hourly;
import com.example.weatherforyou.JSON.OpenWeather.Weather;
import com.example.weatherforyou.data.db.WeatherDatabase;
import com.example.weatherforyou.data.db.entities.WeatherEntity;
import com.example.weatherforyou.data.network.OpenWeatherController;
import com.example.weatherforyou.model.city.City;
import com.example.weatherforyou.model.weatherapi.DailyResponse;
import com.example.weatherforyou.model.weatherapi.ForecastResponse;
import com.example.weatherforyou.model.weatherapi.HourlyResponse;
import com.example.weatherforyou.model.weatherapi.WeatherService;

import java.util.ArrayList;
import java.util.List;

public class OpenWeatherRepository implements RepositoryCallback, WeatherService {


    private MutableLiveData<ForecastResponse> responseMutableLiveData = new MutableLiveData();

    private ForecastResponse weatherforPush;


    WeatherDatabase database = App.AppInstance.getInstance().getDatabase();

    private City city;

    public LiveData<WeatherEntity> getWeatherFromDatabase(String cityName) {
        return database.getWeatherDao().getBy(cityName);
    }


    public void start(double lat, double lon) {
        new OpenWeatherController().start(this, lat, lon);
//        new YandexWeatherControler().start(lat, lon);
        //new WeatherBitController().start(55,45);
    }

    @Override
    public void onSuccess(Weather weather) {
        Log.e("f", "работает?");
        //database.getWeatherDao().insert(new DatabaseMapper(city).toDatabaseModel(weather));
        weatherforPush = map(weather);
        responseMutableLiveData.postValue(map(weather));

    }

    @Override
    public void onFailure() {

    }

    @Override
    public LiveData<ForecastResponse> getResponse() {
        return responseMutableLiveData;
    }

    public ForecastResponse getWeatherforPush() {
        return weatherforPush;
    }

    public void setWeatherforPush(ForecastResponse weatherforPush) {
        this.weatherforPush = weatherforPush;
    }

    public ForecastResponse map(Weather weather) {

        Current current = weather.getCurrent();

        return new ForecastResponse() {
            @Override
            public String getSource() {
                return "openWeather";
            }

            @Override
            public Integer getTimeUpdate() {
                return current.getDt();
            }

            @Override
            public String getIconId() {
                return current.getWeather().get(0).getIcon();
            }

            @Override
            public String getDescription() {
                return current.getWeather().get(0).getDescription() + "," + current.getWeather().get(0).getMain();
            }

            @Override
            public Double getTemp() {
                return current.getTemp();
            }

            @Override
            public Integer getSunRise() {
                return current.getSunrise();
            }

            @Override
            public Integer getSunSet() {
                return current.getSunset();
            }

            @Override
            public Double getPrecipitation() {
//                if (current.getRain() != null) {
//                    return (int) current.getRain().get1h().doubleValue();
//                } else if (current.getSnow() != null) {
//                    return (int) current.getSnow().get1h().doubleValue();
//                } else {
//                    return 0;
//                }
                if (current.getRain() != null) {
                    return current.getRain().get1h();
                }
                return 0.0;
            }

            @Override
            public Integer getPressure() {
                return current.getPressure();
            }

            @Override
            public Integer getHumidity() {
                return current.getHumidity();
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
                return current.getVisibility();
            }

            @Override
            public List<DailyResponse> getDailyResponse() {
                ArrayList<DailyResponse> dailyResponses = new ArrayList<>();

                for (Daily daily : weather.getDaily()) {
                    DailyResponse response = new DailyResponse() {
                        @Override
                        public int getCity() {
                            return weather.getLat().intValue();
                        }

                        @Override
                        public int getTime() {
                            return daily.getDt();
                        }

                        @Override
                        public Double getMinTemp() {
                            return daily.getTemp().getMin();
                        }

                        @Override
                        public Double getMaxTemp() {
                            return daily.getTemp().getMax();
                        }

                        @Override
                        public String getIconWithUrl() {
                            return daily.getWeather().get(0).getIcon();
                        }
                    };
                    dailyResponses.add(response);
                }
                return dailyResponses;
            }

            @Override
            public List<HourlyResponse> getHourlyResponse() {

                ArrayList<HourlyResponse> hourlyResponses = new ArrayList<>();

                for (Hourly hourly : weather.getHourly()) {
                    HourlyResponse response = new HourlyResponse() {
                        @Override
                        public int getCity() {
                            return weather.getLat().intValue();
                        }

                        @Override
                        public int getTime() {
                            return hourly.getDt();
                        }

                        @Override
                        public Double getTemp() {
                            return hourly.getTemp();
                        }

                        @Override
                        public String getIconWithUrl() {
                            return hourly.getWeather().get(0).getIcon();
                        }
                    };
                    hourlyResponses.add(response);
                }
                return hourlyResponses;
            }
        };
    }

    @Override
    public void update(City city) {
        this.city = city;
        start(city.getLat(), city.getLon());
    }
}

