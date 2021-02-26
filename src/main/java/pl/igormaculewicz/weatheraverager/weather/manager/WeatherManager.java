package pl.igormaculewicz.weatheraverager.weather.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.igormaculewicz.weatheraverager.weather.model.LabeledWeatherSummary;
import pl.igormaculewicz.weatheraverager.weather.service.WeatherService;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherManager {

    private final Set<WeatherService> services;

    public List<LabeledWeatherSummary> getSummaryForLocation(double lat, double lng) {
        return services.stream()
                .map(service -> service.getPresentWeatherForLocation(lat, lng))
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Method that realizes functionality of getting weather summary for given city.
     *
     * @param city given city
     * @return weather summary for given city.
     */
    public List<LabeledWeatherSummary> getSummaryForCity(String city) {
        return services.stream()
                .map(service -> service.getPresentWeatherForCity(city))
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());
    }
}
