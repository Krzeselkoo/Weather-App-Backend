package org.krzeselkoo.weatherapp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.krzeselkoo.weatherapp.deserializers.WeatherForecastDeserializer;

import java.util.List;

@JsonDeserialize(using = WeatherForecastDeserializer.class)
@Getter
@Setter
public class WeatherForecast {
    private List<WeatherDetails> forecast;
}