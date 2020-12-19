package com.example.weatherforyou.JSON.Yandex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentY {
    @SerializedName("uptime")
    @Expose
    private Integer dt;
    @SerializedName("temp")
    @Expose
    private Double temperature;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("condition")
    @Expose
    private String description;
    @SerializedName("prec_strength")
    @Expose
    private Double precipitation;
    @SerializedName("wind_speed")
    @Expose
    private Double windSpeed;
    @SerializedName("wind_dir")
    @Expose
    private String windDirection;
    @SerializedName("pressure_pa")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Double humidity;


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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String windDirection() {
        return windDirection;
    }

    public void windDirection(String windDeg) {
        this.windDirection = windDeg;
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
