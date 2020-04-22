package com.monolit.gamesjar.backend.client;

import com.monolit.gamesjar.backend.config.WeatherConfig;
import com.monolit.gamesjar.backend.domain.WeatherDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Component
@Getter
public class WeatherClient {

    @Autowired
    private WeatherConfig weatherConfig;

    @Autowired
    private WeatherSpecifier weatherSpecifier;

    private WeatherDto myWeather;


    public WeatherDto getWeather() {
        URI url = UriComponentsBuilder.fromHttpUrl(weatherConfig.getWeatherEndpoint())
                .queryParam("q", weatherConfig.getWeatherCity())
                .queryParam("appid", weatherConfig.getWeatherKey())
                .queryParam("units", "metric").build().encode().toUri();
        myWeather = weatherSpecifier.specificResult(myWeather, url.toString());

        if(myWeather != null) {
            return myWeather;
        }
        return new WeatherDto();
    }
}
