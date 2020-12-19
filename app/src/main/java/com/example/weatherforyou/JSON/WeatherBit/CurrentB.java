package com.example.weatherforyou.JSON.WeatherBit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentB {
    @SerializedName("ts")
    @Expose
    private Integer dt;
    @SerializedName("temp")
    @Expose
    private Double temperature;
    @SerializedName("precip")
    @Expose
    private Double precipitation;
    @SerializedName("snow")
    @Expose
    private Double precipitationSnow;
    @SerializedName("wind_spd")
    @Expose
    private Double windSpeed;
    @SerializedName("wind_dir")
    @Expose
    private String windDirection;
    @SerializedName("pres")
    @Expose
    private Double pressure;
    @SerializedName("rh")
    @Expose
    private Double humidity;

    public Double getPrecipitationSnow() {
        return precipitationSnow;
    }

    public void setPrecipitationSnow(Double precipitationSnow) {
        this.precipitationSnow = precipitationSnow;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
