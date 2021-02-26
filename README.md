# Weather apis aggregator
Project for passing exercise on studies

### How to run
To build and then run application, you have to simply use given command in working directory:
```shell script
./gradlew bootJar && java -jar build/libs/weather-apis-aggregator-0.0.1-SNAPSHOT.jar
```

### Usage
##### To list all available commands, type:
```shell script
help
```

#### Weather summary
##### By City
##### Pattern:
```shell script
http://localhost:8080/summary/city/<city>
```
##### Example: 
```shell script
http://localhost:8080/summary/city/Koszalin
```
##### By longitude and latitude
##### Pattern:
```shell script
http://localhost:8080/summary/location/lat/{lat}/lng/{lng}
```


#### Averaged weather
##### By City
##### Pattern:
```shell script
http://localhost:8080/average/city/<city>
```
##### Example: 
```shell script
http://localhost:8080/average/city/Koszalin
```

##### By longitude and latitude
##### Pattern:
```shell script
http://localhost:8080/average/location/lat/{lat}/lng/{lng}
```

### FAQ
- For now, application is integrated with given weather apis: WeatherStack, OpenWeatherMap
- To integrate with another one, you have to implement WeatherService interface and register it as a bean.



#### Feel free to improve! :)