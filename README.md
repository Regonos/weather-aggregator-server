# Weather-averager
Project for passing exercise on studies

### How to run
To run build and then run application you have to simply use given command in working directory:
```shell script
./gradlew bootJar && java -jar build/libs/weather-averager-0.0.1-SNAPSHOT.jar
```

### Usage
##### To list all available commands, type:
```shell script
help
```

##### To get weather for given location, type:
```shell script
weather-avg <location>
```
For example: 
```shell script
weather-avg Szczecin
```

### FAQ
- For now, application is integrated with given weather apis: WeatherStack, OpenWeatherMap
- To integrate with another one, you have to implement WeatherService interface and register it as a bean.



#### Feel free to improve! :)