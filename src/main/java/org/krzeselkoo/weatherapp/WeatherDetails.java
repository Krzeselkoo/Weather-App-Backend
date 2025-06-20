package org.krzeselkoo.weatherapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDetails {
    private String time;
    private String weatherCode;
    private double minTemp;
    private double maxTemp;
    private double estimatedEnergy;
    private double sunExposureInSeconds;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("weather_code")
    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    @JsonProperty("temperature_2m_min")
    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    @JsonProperty("temperature_2m_max")
    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    @JsonProperty("sunshine_duration")
    public double getSunExposure(){
        return sunExposureInSeconds;
    }
    public void setSunExposure(double sunExposureInSeconds){
        this.sunExposureInSeconds = sunExposureInSeconds;
    }

    public double getEstimatedEnergy() {
        return estimatedEnergy;
    }

    public void setEstimatedEnergy(double estimatedEnergy) {
        this.estimatedEnergy = estimatedEnergy;
    }

    public void calculateEnergy() {
        double sunExposureInHours = sunExposureInSeconds/3600;
        double panelsEfficiency = 0.2f;

        //unit: kW
        double installationPower = 2.5f;

        //unit: kWh =  kW * efficiency * h
        this.estimatedEnergy = installationPower * panelsEfficiency * sunExposureInHours;
    }

}
