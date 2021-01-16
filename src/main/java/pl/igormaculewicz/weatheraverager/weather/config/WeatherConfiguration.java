package pl.igormaculewicz.weatheraverager.weather.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Optional;


@Getter
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties("weather")
public class WeatherConfiguration {

    private final Map<String, WeatherServiceConfiguration> services;

    public WeatherServiceConfiguration getServiceConfiguration(String serviceName) {
        return Optional.ofNullable(services.get(serviceName))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Cannot find configuration for service with '%s' name!", serviceName)));
    }
}
