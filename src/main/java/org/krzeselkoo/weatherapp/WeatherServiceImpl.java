package org.krzeselkoo.weatherapp;

import org.krzeselkoo.weatherapp.interfaces.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;

    @Value("${api.weather.forecast.url}")
    private String weatherApiUrl;

    @Value("${api.weekly.summary.url}")
    private String weeklySummaryUrl;
    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public WeatherForecast getWeatherForecast(double latitude, double longitude) {
        String apiUrl = String.format(Locale.US, weatherApiUrl, latitude, longitude);
        return restTemplate.getForObject(apiUrl, WeatherForecast.class);
    }
    @Override
    public WeekSummary getWeekSummary(double latitude, double longitude) {
        String apiUrl = String.format(Locale.US, weeklySummaryUrl, latitude, longitude);
        return restTemplate.getForObject(apiUrl, WeekSummary.class);
    }
}