package org.krzeselkoo.weatherapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = WeekSummaryDeserializer.class)
public class WeekSummary {
    private double averagePressure;
    private double maxWeeklyTemperature;
    private double minWeeklyTemperature;
    private double averageSunExposureInSeconds;
    private String rainInformation;

    @JsonProperty("average_pressure")
    public double getAveragePressure() {
        return averagePressure;
    }

    public void setAveragePressure(double averagePressure) {
        this.averagePressure = averagePressure;
    }

    @JsonProperty("max_temperature_in_week")
    public double getMaxWeeklyTemperature() {
        return maxWeeklyTemperature;
    }

    public void setMaxWeeklyTemperature(double maxWeeklyTemperature) {
        this.maxWeeklyTemperature = maxWeeklyTemperature;
    }

    @JsonProperty("min_temperature_in_week")
    public double getMinWeeklyTemperature() {
        return minWeeklyTemperature;
    }

    public void setMinWeeklyTemperature(double minWeeklyTemperature) {
        this.minWeeklyTemperature = minWeeklyTemperature;
    }

    @JsonProperty("average_sun_exposure")
    public double getAverageSunExposureInSeconds() {
        return averageSunExposureInSeconds;
    }

    public void setAverageSunExposureInSeconds(double averageSunExposureInSeconds) {
        this.averageSunExposureInSeconds = averageSunExposureInSeconds;
    }

    @JsonProperty("rain_information")
    public String getRainInformation() {
        return rainInformation;
    }

    public void setRainInformation(String rainInformation) {
        this.rainInformation = rainInformation;
    }
}
