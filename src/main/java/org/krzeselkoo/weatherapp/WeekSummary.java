package org.krzeselkoo.weatherapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Setter;
import org.krzeselkoo.weatherapp.deserializers.WeekSummaryDeserializer;



@JsonDeserialize(using = WeekSummaryDeserializer.class)
@Setter
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

    @JsonProperty("max_temperature_in_week")
    public double getMaxWeeklyTemperature() {
        return maxWeeklyTemperature;
    }

    @JsonProperty("min_temperature_in_week")
    public double getMinWeeklyTemperature() {
        return minWeeklyTemperature;
    }

    @JsonProperty("average_sun_exposure")
    public double getAverageSunExposureInSeconds() {
        return averageSunExposureInSeconds;
    }

    @JsonProperty("rain_information")
    public String getRainInformation() {
        return rainInformation;
    }

}
