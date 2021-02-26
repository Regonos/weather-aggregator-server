package pl.igormaculewicz.weatheraverager.weather.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.igormaculewicz.weatheraverager.utils.JsonUtils;
import pl.igormaculewicz.weatheraverager.weather.config.WeatherConfiguration;
import pl.igormaculewicz.weatheraverager.weather.config.WeatherServiceConfiguration;
import pl.igormaculewicz.weatheraverager.weather.model.LabeledWeatherSummary;
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

    private static final String SERVICE_NAME = "openweathermap";
    private static final String CURRENT_WEATHER_FOR_CITY_URL_FORMAT = "/data/2.5/weather?appid=%s&q=%s&units=metric";
    private static final String CURRENT_WEATHER_FOR_LOCATION_URL_FORMAT = "/data/2.5/weather?appid=%s&lat=%s&lon=%s&units=metric";

    private final WeatherServiceConfiguration configuration;
    private final RestTemplate restTemplate;

    @Autowired
    public OpenWeatherMapService(WeatherConfiguration weatherConfiguration, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.configuration = weatherConfiguration.getServiceConfiguration(SERVICE_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledWeatherSummary getPresentWeatherForCity(String city) {

        String response = restTemplate.getForObject(assembleUrlForCity(city), String.class);

        if (Objects.isNull(JsonUtils.findRecursively(response, "main"))) {
            log.error("Skipping OpenWeatherMap service because cannot get metrics!");
            return null;
        }

        return handleResponse(response);
    }

    @Override
    public LabeledWeatherSummary getPresentWeatherForLocation(double lat, double lng) {

        String response = restTemplate.getForObject(assembleUrlForLocation(lat, lng), String.class);

        return handleResponse(response);
    }

    private LabeledWeatherSummary handleResponse(String response) {

        Number temperature = JsonUtils.findRecursively(response, "main.temp");
        Number pressure = JsonUtils.findRecursively(response, "main.pressure");
        Number humidity = JsonUtils.findRecursively(response, "main.humidity");

        return LabeledWeatherSummary.builder()
                .label(configuration.getLabel())
                .summary(new WeatherSummary(temperature, pressure, humidity))
                .build();
    }

    private String assembleUrlForCity(String location) {
        return String.format(configuration.getBaseUrl() + CURRENT_WEATHER_FOR_CITY_URL_FORMAT, configuration.getApiKey(), location);
    }

    private String assembleUrlForLocation(double lat, double lng) {
        return String.format(configuration.getBaseUrl() + CURRENT_WEATHER_FOR_LOCATION_URL_FORMAT, configuration.getApiKey(), lat, lng);
    }
}
