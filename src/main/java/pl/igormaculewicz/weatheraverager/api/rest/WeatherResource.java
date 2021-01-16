package pl.igormaculewicz.weatheraverager.api.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.igormaculewicz.weatheraverager.weather.manager.WeatherManager;
import pl.igormaculewicz.weatheraverager.weather.model.LabeledWeatherSummary;
import pl.igormaculewicz.weatheraverager.weather.model.WeatherSummary;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController("weather")
public class WeatherResource {

    private final WeatherManager manager;

    @GetMapping(path = "/average/city/{city}")
    public WeatherSummary getAveragedWeatherForCity(@PathVariable String city) {
        log.info("Someone trying to obtain averaged weather for: {}", city);
        return manager.getAveragedForCity(city);
    }

    @GetMapping(path = "/summary/city/{city}")
    public List<LabeledWeatherSummary> getWeatherSummaryForCity(@PathVariable String city) {
        log.info("Someone trying to obtain weather summary for: {}", city);
        return manager.getSummaryForCity(city);
    }
}
