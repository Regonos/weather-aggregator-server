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
import pl.igormaculewicz.weatheraverager.weather.model.WeatherSummary;

import java.util.List;


@Slf4j
@CrossOrigin
@Profile("mock")
@RequiredArgsConstructor
@RestController("weather")
public class WeatherMockResource {

    private final WeatherManager manager;

    @GetMapping(path = "/summary/city/{city}")
    public List<LabeledWeatherSummary> getWeatherSummaryForCity(@PathVariable String city) {
        log.info("Someone trying to obtain mock weather summary for: {} city", city);

        return mockSummary();
    }

    @GetMapping(path = "/summary/location/lat/{lat}/lng/{lng}")
    public List<LabeledWeatherSummary> getWeatherSummaryForCity(@PathVariable("lat") double lat, @PathVariable("lng") double lng) {
        log.info("Someone trying to obtain mock weather summary for: Latitude: {}, Longitude: {}", lat, lng);

        return mockSummary();
    }

    private List<LabeledWeatherSummary> mockSummary() {
        WeatherSummary weatherSummary = new WeatherSummary(123, 123, 123);

        return List.of(
            LabeledWeatherSummary.builder().label("service1")
                .summary(weatherSummary)
                .build(),
            LabeledWeatherSummary.builder().label("service2")
                .summary(weatherSummary)
                .build(),
            LabeledWeatherSummary.builder().label("service3")
                .summary(weatherSummary)
                .build(),
            LabeledWeatherSummary.builder().label("service4")
                .summary(weatherSummary)
                .build(),
            LabeledWeatherSummary.builder().label("service5")
                .summary(weatherSummary)
                .build(),
            LabeledWeatherSummary.builder().label("service6")
                .summary(weatherSummary)
                .build(),
            LabeledWeatherSummary.builder().label("service7")
                .summary(weatherSummary)
                .build(),
            LabeledWeatherSummary.builder().label("service8")
                .summary(weatherSummary)
                .build()
        );
    }
}
