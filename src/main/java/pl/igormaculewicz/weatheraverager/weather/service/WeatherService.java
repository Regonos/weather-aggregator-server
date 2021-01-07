package pl.igormaculewicz.weatheraverager.weather.service;

import pl.igormaculewicz.weatheraverager.weather.model.WeatherSummary;

public interface WeatherService {

    /**
     * Method that return a basic information about weather based on given location.
     *
     * @param location given location.
     * @return weather summary.
     */
    WeatherSummary getPresentWeatherForLocation(String location);
}
