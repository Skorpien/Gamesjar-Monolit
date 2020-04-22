package com.monolit.gamesjar.backend.scheduler;

import com.monolit.gamesjar.backend.controller.WeatherController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherScheduler {

    @Autowired
    private WeatherController weatherController;

   // @Scheduled(cron = "* */10 * * * *")
    public void refreshWeather() {
        weatherController.getWeather();
        System.out.println("SCHEDULE");
    }

}
