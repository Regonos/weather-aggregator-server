package pl.igormaculewicz.weatheraverager.api.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.igormaculewicz.weatheraverager.weather.manager.WeatherManager;
import pl.igormaculewicz.weatheraverager.weather.model.LabeledWeatherSummary;

import java.util.List;


@Slf4j
@CrossOrigin
@Profile("!mock")
@RequiredArgsConstructor
@RestController("weather")
public class WeatherResource {

    private final WeatherManager manager;

    @GetMapping(path = "/summary/city/{city}")
    public List<LabeledWeatherSummary> getWeatherSummaryForCity(@PathVariable String city) {
        log.info("Someone trying to obtain weather summary for: {} city", city);
        return manager.getSummaryForCity(city);
    }

    @GetMapping(path = "/summary/location/lat/{lat}/lng/{lng}")
    public List<LabeledWeatherSummary> getWeatherSummaryForCity(@PathVariable("lat") double lat, @PathVariable("lng") double lng) {
        log.info("Someone trying to obtain weather summary for: Latitude: {}, Longitude: {}", lat, lng);
        return manager.getSummaryForLocation(lat, lng);
    }
}
