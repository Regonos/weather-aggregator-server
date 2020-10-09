package pl.igormaculewicz.weatheraverager.weather.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.igormaculewicz.weatheraverager.utils.JsonUtils;
import pl.igormaculewicz.weatheraverager.weather.model.WeatherSummary;
import pl.igormaculewicz.weatheraverager.weather.service.WeatherService;

import java.util.Objects;


/**
 * https://weatherstack.com/quickstart
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OpenWeatherMapService implements WeatherService {

    @Value("${weather.openweathermap.api-key}")
    private String apiKey;

    @Value("${weather.openweathermap.main-url:http://api.openweathermap.org}")
    private String mainUrl;

    private final RestTemplate restTemplate;

    private static final String CURRENT_WEATHER_URL_FORMAT = "/data/2.5/weather?appid=%s&q=%s&units=metric";

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherSummary getPresentWeatherForLocation(String location) {

        String response = restTemplate.getForObject(assembleUrlForCity(location), String.class);

        if (Objects.isNull(JsonUtils.findRecursively(response, "main"))) {
            log.error("Skipping OpenWeatherMap service because cannot get metrics!");
            return null;
        }

        Double temperature = JsonUtils.findRecursively(response, "main.temp");
        Integer pressure = JsonUtils.findRecursively(response, "main.pressure");
        Integer humidity = JsonUtils.findRecursively(response, "main.humidity");

        return new WeatherSummary(temperature, pressure.doubleValue(), humidity.doubleValue());
    }


    private String assembleUrlForCity(String location) {
        return String.format(mainUrl + CURRENT_WEATHER_URL_FORMAT, apiKey, location);
    }
}
