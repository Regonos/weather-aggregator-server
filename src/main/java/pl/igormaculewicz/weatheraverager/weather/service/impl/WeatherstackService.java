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
public class WeatherstackService implements WeatherService {

    @Value("${weather.weatherstack.api-key}")
    private String apiKey;

    @Value("${weather.weatherstack.main-url:http://api.weatherstack.com}")
    private String mainUrl;

    private final RestTemplate restTemplate;

    private static final String CURRENT_WEATHER_URL_FORMAT = "/current?access_key=%s&query=%s";

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherSummary getPresentWeatherForLocation(String location) {

        String response = restTemplate.getForObject(assembleUrlForCity(location), String.class);

        if (Objects.isNull(JsonUtils.findRecursively(response, "current"))) {
            log.error("Skipping Weatherstack service because cannot get metrics!");
            return null;
        }

        Integer temperature = JsonUtils.findRecursively(response, "current.temperature");
        Integer pressure = JsonUtils.findRecursively(response, "current.pressure");
        Integer humidity = JsonUtils.findRecursively(response, "current.humidity");

        return new WeatherSummary(temperature.doubleValue(), pressure.doubleValue(), humidity.doubleValue());
    }


    private String assembleUrlForCity(String location) {
        return String.format(mainUrl + CURRENT_WEATHER_URL_FORMAT, apiKey, location);
    }
}
