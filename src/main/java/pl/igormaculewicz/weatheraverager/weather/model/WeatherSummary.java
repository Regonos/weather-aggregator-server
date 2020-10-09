package pl.igormaculewicz.weatheraverager.weather.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WeatherSummary {
    private final Double temperature;
    private final Double pressure;
    private final Double humidity;
}
