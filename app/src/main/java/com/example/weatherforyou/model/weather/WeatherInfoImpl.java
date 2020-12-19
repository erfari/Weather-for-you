package com.example.weatherforyou.model.weather;

import com.example.weatherforyou.JSON.OpenWeather.Daily;

import java.util.List;

public class WeatherInfoImpl implements WeatherInfo {

    private String temperature;

    private String humidity;

    private String visibility;

    private String windSpeed;

    private String windDirection;

    private String precipitation;

    private String pressure;

    private String sunrise;

    private String sunset;

    public WeatherInfoImpl(String temperature, String humidity, String visibility, String windSpeed, String windDirection, String precipitation, String pressure, String sunrise, String sunset) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.precipitation = precipitation;
        this.pressure = pressure;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    @Override
    public String getTemperature() {
        return temperature;
    }

    @Override
    public String getHumidity() {
        return humidity;
    }

    @Override
    public String getVisibility() {
        return visibility;
    }

    @Override
    public String getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String getWindDirection() {
        return windDirection;
    }

    @Override
    public String getPrecipitation() {
        return precipitation;
    }


    public String getPressure() {
        return pressure;
    }

    @Override
    public String getSunrise() {
        return sunrise;
    }

    @Override
    public String getSunset() {
        return sunset;
    }

    @Override
    public List<HourWeather> getHourlyWeather() {
        return null;
    }

    @Override
    public List<Daily> getDailyWeather() {
        return null;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", visibility='" + visibility + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", precipitation='" + precipitation + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }
}
