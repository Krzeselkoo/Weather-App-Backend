package org.krzeselkoo.weatherapp;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoordinatesRequest {
    @Min(value = -90, message = "Latitude must be greater than or equal to -90.")
    @Max(value = 90, message = "Latitude must be less than or equal to 90.")
    private double latitude;

    @Min(value = -180, message = "Longitude must be greater than or equal to -180.")
    @Max(value = 180, message = "Longitude must be less than or equal to 180.")
    private double longitude;
}