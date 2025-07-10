package org.krzeselkoo.weatherapp;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.krzeselkoo.weatherapp.interfaces.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final WeatherService weatherService;

    @GetMapping("/weather-forecast")
    public ResponseEntity<?> getWeatherForecast(@Valid CoordinatesRequest coordinates) {
        try {
            WeatherForecast weatherForecast = weatherService.getWeatherForecast(
                    coordinates.getLatitude(), coordinates.getLongitude()
            );

            if (weatherForecast != null) {
                return ResponseEntity.ok(weatherForecast.getForecast());
            } else {
                return ResponseEntity.status(500).body("Failed to fetch weather details from the API.");
            }
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body("HTTP error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/week-summary")
    public ResponseEntity<?> getWeekSummary(@Valid CoordinatesRequest coordinates) {
        try {
            WeekSummary weekSummary = weatherService.getWeekSummary(
                    coordinates.getLatitude(), coordinates.getLongitude()
            );

            if (weekSummary != null) {
                return ResponseEntity.ok(weekSummary);
            } else {
                return ResponseEntity.status(500).body("Failed to fetch weather details from the API.");
            }
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body("HTTP error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
        }
    }
}