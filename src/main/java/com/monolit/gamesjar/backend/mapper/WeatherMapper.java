package com.monolit.gamesjar.backend.mapper;

import com.monolit.gamesjar.backend.domain.Weather;
import com.monolit.gamesjar.backend.domain.WeatherDto;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {

    public Weather mapToWeather(final WeatherDto weatherDto) {
        return new Weather(
                weatherDto.getTemp(),
                weatherDto.getHumidity()
        );
    }

    public WeatherDto mapToWeatherDto(final Weather weather) {
        return new WeatherDto(
                weather.getTemp(),
                weather.getHumidity()
        );
    }
}
