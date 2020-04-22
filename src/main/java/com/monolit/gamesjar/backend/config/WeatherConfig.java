package com.monolit.gamesjar.backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WeatherConfig {

    @Value("${weather.api.endpoint.prod}")
    private String weatherEndpoint;

    @Value("${weather.app.key}")
    private String weatherKey;

    @Value("${weather.app.city}")
    private String weatherCity;
}
