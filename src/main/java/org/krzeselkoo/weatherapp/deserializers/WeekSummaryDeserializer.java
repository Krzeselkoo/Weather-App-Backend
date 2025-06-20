package org.krzeselkoo.weatherapp.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.krzeselkoo.weatherapp.WeekSummary;


import java.io.IOException;

public class WeekSummaryDeserializer extends JsonDeserializer<WeekSummary> {
    @Override
    public WeekSummary deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode rootNode = parser.getCodec().readTree(parser);
        JsonNode dailyNode = rootNode.get("daily");
        JsonNode hourlyNode = rootNode.get("hourly");

        JsonNode pressureMsl = hourlyNode.get("pressure_msl");
        JsonNode sunExposure = dailyNode.get("sunshine_duration");
        JsonNode maxTemperatures = dailyNode.get("temperature_2m_max");
        JsonNode minTemperatures = dailyNode.get("temperature_2m_min");
        JsonNode weatherCodes = dailyNode.get("weather_code");

        double averagePressure = calculateAverage(pressureMsl);
        double averageSunExposure = calculateAverage(sunExposure);
        double maxWeeklyTemperature = findMax(maxTemperatures);
        double minWeeklyTemperature = findMin(minTemperatures);
        String rainInformation = checkIfWeekWasRainy(weatherCodes);

        WeekSummary weekSummary = new WeekSummary();
        weekSummary.setAveragePressure(averagePressure);
        weekSummary.setAverageSunExposureInSeconds(averageSunExposure);
        weekSummary.setMaxWeeklyTemperature(maxWeeklyTemperature);
        weekSummary.setMinWeeklyTemperature(minWeeklyTemperature);
        weekSummary.setRainInformation(rainInformation);

        return weekSummary;
    }

    private double calculateAverage(JsonNode arrayNode) {
        arrayValidation(arrayNode);

        double sum = 0.0;
        for (JsonNode valueNode : arrayNode) {
            sum += valueNode.asDouble();
        }

        return sum / arrayNode.size();
    }

    private double findMax(JsonNode arrayNode) {
        arrayValidation(arrayNode);

        double max = Double.NEGATIVE_INFINITY;
        for (JsonNode valueNode : arrayNode) {
            double value = valueNode.asDouble();
            if (value > max) {
                max = value;
            }
        }

        return max;
    }

    private double findMin(JsonNode arrayNode) {
        arrayValidation(arrayNode);

        double min = Double.POSITIVE_INFINITY;
        for (JsonNode valueNode : arrayNode) {
            double value = valueNode.asDouble();
            if (value < min) {
                min = value;
            }
        }

        return min;
    }

    private String checkIfWeekWasRainy(JsonNode arrayNode){
        arrayValidation(arrayNode);

        int counter = 0;
        for(JsonNode valueNode : arrayNode){
            double value = valueNode.asDouble();
            if(value > 50){
                counter--;
            }else{
                counter++;
            }
        }

        return counter > 0 ? "Without precipitation" : "With precipitation";
    }

    private void arrayValidation(JsonNode arrayNode){
        if (arrayNode == null || !arrayNode.isArray() || arrayNode.isEmpty()) {
            throw new IllegalArgumentException("Invalid or empty array node.");
        }
    }

}
