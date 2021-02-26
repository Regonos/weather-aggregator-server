package pl.igormaculewicz.weatheraverager.weather.service.impl;

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
public class WeatherstackService implements WeatherService {

    private static final String SERVICE_NAME = "weatherstack";
    private static final String CURRENT_WEATHER_FOR_CITY_URL_FORMAT = "/current?access_key=%s&query=%s";

    private final WeatherServiceConfiguration configuration;
    private final RestTemplate restTemplate;

    @Autowired
    public WeatherstackService(WeatherConfiguration weatherConfiguration, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.configuration = weatherConfiguration.getServiceConfiguration(SERVICE_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledWeatherSummary getPresentWeatherForCity(String city) {
        String response = restTemplate.getForObject(assembleUrlForCity(city), String.class);

        return handleResponse(response);
    }

    @Override
    public LabeledWeatherSummary getPresentWeatherForLocation(double lat, double lng) {
        String response = restTemplate.getForObject(assembleUrlForLocation(lat, lng), String.class);

        return handleResponse(response);
    }

    private LabeledWeatherSummary handleResponse(String response) {
        if (Objects.isNull(JsonUtils.findRecursively(response, "current"))) {
            log.error("Skipping Weatherstack service because cannot get metrics!");
            return null;
        }

        Number temperature = JsonUtils.findRecursively(response, "current.temperature");
        Number pressure = JsonUtils.findRecursively(response, "current.pressure");
        Number humidity = JsonUtils.findRecursively(response, "current.humidity");

        return LabeledWeatherSummary.builder()
                .label(configuration.getLabel())
                .summary(new WeatherSummary(temperature, pressure, humidity))
                .build();
    }

    private String assembleUrlForCity(String location) {
        return assembleUrl(location);
    }

    private String assembleUrlForLocation(double lat, double lng) {
        String query = String.join(",", String.valueOf(lat), String.valueOf(lng));

        return assembleUrl(query);
    }

    private String assembleUrl(String location) {
        return String.format(configuration.getBaseUrl() + CURRENT_WEATHER_FOR_CITY_URL_FORMAT, configuration.getApiKey(), location);
    }
}
