package org.krzeselkoo.weatherapp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.krzeselkoo.weatherapp.deserializers.WeatherForecastDeserializer;

import java.util.List;

@JsonDeserialize(using = WeatherForecastDeserializer.class)
public class WeatherForecast {
    private List<WeatherDetails> forecast;

    public List<WeatherDetails> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherDetails> forecast) {
        this.forecast = forecast;
    }
}