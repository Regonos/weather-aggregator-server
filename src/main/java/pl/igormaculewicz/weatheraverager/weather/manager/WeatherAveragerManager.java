package pl.igormaculewicz.weatheraverager.weather.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.igormaculewicz.weatheraverager.weather.service.WeatherService;
import pl.igormaculewicz.weatheraverager.weather.model.WeatherSummary;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherAveragerManager {

    private final Set<WeatherService> services;

    public WeatherSummary averagedWeatherForLocation(String location) {

        List<WeatherSummary> summaries = services.stream()
                .map(service -> service.getPresentWeatherForLocation(location))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Double temperatureSummary = summaries.stream().mapToDouble(WeatherSummary::getTemperature).average().orElse(Double.MIN_VALUE);
        Double pressureSummary = summaries.stream().mapToDouble(WeatherSummary::getPressure).average().orElse(Double.MIN_VALUE);
        Double humiditySummary = summaries.stream().mapToDouble(WeatherSummary::getHumidity).average().orElse(Double.MIN_VALUE);

        return new WeatherSummary(temperatureSummary, pressureSummary, humiditySummary);
    }
}
