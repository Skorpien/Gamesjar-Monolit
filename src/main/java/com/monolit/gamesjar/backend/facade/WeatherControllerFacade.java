package com.monolit.gamesjar.backend.facade;

import com.monolit.gamesjar.backend.client.WeatherClient;
import com.monolit.gamesjar.backend.domain.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherControllerFacade {

    @Autowired
    private WeatherClient weatherClient;

    public WeatherDto getWeather() {
        return weatherClient.getWeather();
    }
}
