package org.krzeselkoo.weatherapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


public class WeatherDetails {
    @Getter
    @Setter
    private String time;

    @Setter
    private String weatherCode;

    @Setter
    private double minTemp;

    @Setter
    private double maxTemp;

    @Getter
    @Setter
    private double estimatedEnergy;

    private double sunExposureInSeconds;

    @JsonProperty("weather_code")
    public String getWeatherCode() {
        return weatherCode;
    }

    @JsonProperty("temperature_2m_min")
    public double getMinTemp() {
        return minTemp;
    }

    @JsonProperty("temperature_2m_max")
    public double getMaxTemp() {
        return maxTemp;
    }

    @JsonProperty("sunshine_duration")
    public double getSunExposure(){
        return sunExposureInSeconds;
    }
    public void setSunExposure(double sunExposureInSeconds){
        this.sunExposureInSeconds = sunExposureInSeconds;
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
