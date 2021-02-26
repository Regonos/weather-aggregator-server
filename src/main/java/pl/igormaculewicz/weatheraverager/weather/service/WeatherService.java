package pl.igormaculewicz.weatheraverager.weather.service;

import pl.igormaculewicz.weatheraverager.weather.model.LabeledWeatherSummary;

public interface WeatherService {

    /**
     * Method that return a basic information about weather based on given location.
     *
     * @param city given location.
     * @return weather summary.
     */
    LabeledWeatherSummary getPresentWeatherForCity(String city);

    LabeledWeatherSummary getPresentWeatherForLocation(double lat, double lng);
}
