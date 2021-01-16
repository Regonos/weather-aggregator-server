package pl.igormaculewicz.weatheraverager.weather.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LabeledWeatherSummary {

    private final String label;
    private final WeatherSummary summary;
}