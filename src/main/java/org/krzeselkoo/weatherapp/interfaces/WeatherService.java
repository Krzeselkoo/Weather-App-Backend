package org.krzeselkoo.weatherapp.interfaces;

import org.krzeselkoo.weatherapp.WeatherForecast;
import org.krzeselkoo.weatherapp.WeekSummary;

public interface WeatherService {
    WeatherForecast getWeatherForecast(double latitude, double longitude);
    WeekSummary getWeekSummary(double latitude, double longitude);
}