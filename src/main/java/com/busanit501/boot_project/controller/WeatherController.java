package com.busanit501.boot_project.controller;

import com.busanit501.boot_project.dto.WeatherDTO;
import com.busanit501.boot_project.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    // 생성자 주입
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/board/weather")
    public String weather(Model model) {
        WeatherDTO weather = weatherService.getWeatherData();
        model.addAttribute("weatherData", weather);
        return "board/weather";
    }

}
