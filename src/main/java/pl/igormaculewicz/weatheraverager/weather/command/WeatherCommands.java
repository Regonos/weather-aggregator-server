package pl.igormaculewicz.weatheraverager.weather.command;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pl.igormaculewicz.weatheraverager.weather.manager.WeatherAveragerManager;
import pl.igormaculewicz.weatheraverager.weather.model.WeatherSummary;

@RequiredArgsConstructor
@ShellComponent("Commands for checking a weather")
public class WeatherCommands {

    private final WeatherAveragerManager weatherAveragerManager;

    @ShellMethod(value = "Checking weather in all implemented weather services.", key = "weather-avg")
    public void checkAveragedWeather(
            @ShellOption(value = {"-c", "--city"}, help = "Specify which time weather should be presented") String city
    ) {
        WeatherSummary summary = weatherAveragerManager.averagedWeatherForLocation(city);
        System.out.println(String.format("Weather for %s: Temperature: %.0f, Pressure: %.0f, Humidity: %.0f", city, summary.getTemperature(), summary.getPressure(), summary.getHumidity()));
    }
}
