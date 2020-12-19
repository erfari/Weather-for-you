package com.example.weatherforyou.model.weather;

public class WeatherInfoUiModelImpl implements WeatherInfoUIModel {

    private String parameterName;

    private String parameterValue;

    public WeatherInfoUiModelImpl(String parameterName, String parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }

    @Override
    public String getParameterName() {
        return parameterName;
    }

    @Override
    public String getParameterValue() {
        return parameterValue;
    }
}
