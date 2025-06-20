# Weather App Backend

This is the backend for a weather application built with Spring Boot.  
The app provides weather data and weekly summaries through RESTful APIs.  
You can find the frontend repository here: [weather-app-frontend GitHub](https://github.com/Krzeselkoo/Weather-App-Frontend) 

## About

This project is the backend for a weather application.  
It fetches weather data from external APIs, processes it, and serves it to the frontend.  
The backend is designed to handle geolocation-based weather requests and provide accurate forecasts.

**Features:**
- Provides a 7-day weather forecast based on latitude and longitude
- Calculates weekly weather summaries, including estimated solar energy production per day
- Handles CORS for secure communication with the frontend
- Error handling for invalid requests and external API issues

## Requirements

- Java 21 or higher
- Maven 3.8 or higher
- Docker (optional, for containerized deployment)

## Endpoints

- **GET /api/weather-forecast**  
  Returns a 7-day weather forecast for the given latitude and longitude.  
  **Parameters:**
    - `latitude` (required): Latitude of the location
    - `longitude` (required): Longitude of the location

- **GET /api/week-summary**  
  Returns a weekly summary, e.g., the average pressure level.  
  **Parameters:**
    - `latitude` (required): Latitude of the location
    - `longitude` (required): Longitude of the location

## Roadmap

- Add support for additional weather metrics
- Implement caching for frequently requested locations
- Add authentication and rate limiting for API usage

## Deployment

You can deploy the backend using Docker.  
Build the Docker image and run the container as follows:

```bash
docker build -t weatherapp-backend .
docker run -p 8080:8080 weatherapp-backend
```

Ensure the backend is accessible at [http://localhost:8080](http://localhost:8080) or your chosen deployment URL.