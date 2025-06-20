package org.krzeselkoo.weatherapp.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.krzeselkoo.weatherapp.WeatherDetails;
import org.krzeselkoo.weatherapp.WeatherForecast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecastDeserializer extends JsonDeserializer<WeatherForecast> {

    @Override
    public WeatherForecast deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        JsonNode rootNode = parser.getCodec().readTree(parser);
        JsonNode dailyNode = rootNode.get("daily");

        List<WeatherDetails> forecast = new ArrayList<>();
        if (dailyNode != null) {
            JsonNode timeNode = dailyNode.get("time");
            JsonNode weatherCodeNode = dailyNode.get("weather_code");
            JsonNode sunshineDurationNode = dailyNode.get("sunshine_duration");
            JsonNode tempMaxNode = dailyNode.get("temperature_2m_max");
            JsonNode tempMinNode = dailyNode.get("temperature_2m_min");

            int size = timeNode.size();
            if (weatherCodeNode.size() != size || sunshineDurationNode.size() != size ||
                    tempMaxNode.size() != size || tempMinNode.size() != size) {
                throw new IllegalArgumentException("Array lengths in 'daily' object are inconsistent.");
            }

            for (int i = 0; i < timeNode.size(); i++) {
                WeatherDetails details = new WeatherDetails();
                details.setTime(timeNode.get(i).asText());
                details.setWeatherCode(weatherCodeNode.get(i).asText());
                details.setSunExposure(sunshineDurationNode.get(i).asDouble());
                details.setMaxTemp(tempMaxNode.get(i).asDouble());
                details.setMinTemp(tempMinNode.get(i).asDouble());
                details.calculateEnergy();
                forecast.add(details);
            }
        }

        WeatherForecast weatherForecast = new WeatherForecast();
        weatherForecast.setForecast(forecast);
        return weatherForecast;
    }
}