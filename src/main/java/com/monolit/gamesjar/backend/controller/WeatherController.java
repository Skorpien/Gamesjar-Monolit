package com.monolit.gamesjar.backend.controller;

import com.monolit.gamesjar.backend.domain.WeatherDto;
import com.monolit.gamesjar.backend.facade.WeatherControllerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/gamesjar")
public class WeatherController {

    @Autowired
    private WeatherControllerFacade facade;

    @RequestMapping(method = RequestMethod.GET, value = "/weather")
    public WeatherDto getWeather() {
           return facade.getWeather();
    }

}
