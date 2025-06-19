package org.krzeselkoo.weatherapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetWeatherForecast_ValidRequest() throws Exception {
        mockMvc.perform(get("/api/weather-forecast")
                        .param("latitude", "52.22")
                        .param("longitude", "21.01"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetWeatherForecast_InvalidLatitude() throws Exception {
        mockMvc.perform(get("/api/weather-forecast")
                        .param("latitude", "200")
                        .param("longitude", "21.01"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Latitude must be between -90 and 90."));
    }

    @Test
    public void testGetWeekSummary_ValidRequest() throws Exception {
        mockMvc.perform(get("/api/week-summary")
                        .param("latitude", "52.22")
                        .param("longitude", "21.01"))
                .andExpect(status().isOk());
    }
}