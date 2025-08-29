package com.theonewhocodes.springsecurity.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @GetMapping
    public String getWeather() {
        return "Current weather data: Sunny, 25°C";
    }

    @PostMapping
    public String addWeatherData(){
        return "Weather data added successfully";
    }

    @DeleteMapping
    public String deleteWeatherData() {
        return "Weather data deleted!!!";
    }
}
