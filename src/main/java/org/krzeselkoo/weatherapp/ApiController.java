package org.krzeselkoo.weatherapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final RestTemplate restTemplate;

    public ApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/weather-forecast")
    public ResponseEntity<?> getWeatherForecast(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude
    ) {
        if (latitude < -90 || latitude > 90) {
            return ResponseEntity.badRequest().body("Latitude must be between -90 and 90.");
        }
        if (longitude < -180 || longitude > 180) {
            return ResponseEntity.badRequest().body("Longitude must be between -180 and 180.");
        }

        String apiUrl = String.format(Locale.US,
                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&daily=weather_code,sunshine_duration,temperature_2m_max,temperature_2m_min&timezone=Europe/Berlin",
                latitude, longitude);

        try {
            WeatherForecast weatherForecast = restTemplate.getForObject(apiUrl, WeatherForecast.class);

            if (weatherForecast != null) {
                return ResponseEntity.ok(weatherForecast.getForecast());
            } else {
                return ResponseEntity.status(500).body("Failed to fetch weather details from the API.");
            }
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body("Client error: " + e.getMessage());
        } catch (HttpServerErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body("Server error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/week-summary")
    public ResponseEntity<?> getWeekSummary(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude
    ) {
        if (latitude < -90 || latitude > 90) {
            return ResponseEntity.badRequest().body("Latitude must be between -90 and 90.");
        }
        if (longitude < -180 || longitude > 180) {
            return ResponseEntity.badRequest().body("Longitude must be between -180 and 180.");
        }

        String apiUrl = String.format(Locale.US,
                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&daily=weather_code,sunshine_duration,temperature_2m_max,temperature_2m_min&timezone=Europe/Berlin&hourly=pressure_msl",
                latitude, longitude);

        try {
            WeekSummary weekSummary = restTemplate.getForObject(apiUrl, WeekSummary.class);

            if (weekSummary != null) {
                return ResponseEntity.ok(weekSummary);
            } else {
                return ResponseEntity.status(500).body("Failed to fetch weather details from the API.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching weather details: " + e.getMessage());
        }
    }
}