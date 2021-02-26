package pl.igormaculewicz.weatheraverager.weather.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WeatherSummary {
    private final Number temperature;
    private final Number pressure;
    private final Number humidity;
}
