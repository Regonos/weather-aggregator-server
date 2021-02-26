package pl.igormaculewicz.weatheraverager.api.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.igormaculewicz.weatheraverager.weather.manager.WeatherManager;
import pl.igormaculewicz.weatheraverager.weather.model.LabeledWeatherSummary;
import pl.igormaculewicz.weatheraverager.weather.model.WeatherSummary;

import java.util.List;


@Slf4j
@CrossOrigin
@RequiredArgsConstructor
//@RestController("weather")
public class WeatherMockResource {

    private final WeatherManager manager;

    @GetMapping(path = "/summary/city/{city}")
    public List<LabeledWeatherSummary> getWeatherSummaryForCity(@PathVariable String city) {
        log.info("Someone trying to obtain weather summary for: {} city", city);
        return manager.getSummaryForCity(city);
    }

    @GetMapping(path = "/summary/location/lat/{lat}/lng/{lng}")
    public List<LabeledWeatherSummary> getWeatherSummaryForCity(@PathVariable("lat") double lat, @PathVariable("lng") double lng) {
        WeatherSummary weatherSummary = new WeatherSummary(123, 123, 123);

        return List.of(
                LabeledWeatherSummary.builder().label("service1")
                        .summary(weatherSummary)
                        .build(),
                LabeledWeatherSummary.builder().label("service1")
                        .summary(weatherSummary)
                        .build(),
                LabeledWeatherSummary.builder().label("service1")
                        .summary(weatherSummary)
                        .build(),
                LabeledWeatherSummary.builder().label("service1")
                        .summary(weatherSummary)
                        .build(),
                LabeledWeatherSummary.builder().label("service1")
                        .summary(weatherSummary)
                        .build(),
                LabeledWeatherSummary.builder().label("service1")
                        .summary(weatherSummary)
                        .build(),
                LabeledWeatherSummary.builder().label("service1")
                        .summary(weatherSummary)
                        .build(),
                LabeledWeatherSummary.builder().label("service2")
                        .summary(weatherSummary)
                        .build()
        );
    }
}
