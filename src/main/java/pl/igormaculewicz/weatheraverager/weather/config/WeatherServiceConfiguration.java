package pl.igormaculewicz.weatheraverager.weather.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.constraints.NotBlank;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
public class WeatherServiceConfiguration {

    @NotBlank
    private final String label;

    @NotBlank
    private final String baseUrl;

    @NotBlank
    private final String apiKey;
}
