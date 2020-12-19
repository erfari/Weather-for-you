package com.example.weatherforyou.model.weatherapi;

import java.util.List;

public interface ForecastResponse {

    String getSource();

    Integer getTimeUpdate();

    String getIconId();

    String getDescription();

    Double getTemp();

    Integer getSunRise();

    Integer getSunSet();

    Double getPrecipitation();

    Integer getPressure();

    Integer getHumidity();

    Double getWindSpeed();

    Double getWindDeg();

    Integer getVisibility();

    List<? extends DailyResponse> getDailyResponse();

    List<? extends HourlyResponse> getHourlyResponse();
}
