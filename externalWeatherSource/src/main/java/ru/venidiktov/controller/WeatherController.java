package ru.venidiktov.controller;

import java.time.LocalDateTime;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.venidiktov.model.WeatherRs;

@Slf4j
@RestController
@RequestMapping(path = "/api/weather")
public class WeatherController {

    private final static Random random = new Random();

    @GetMapping
    ResponseEntity<WeatherRs> getWeather(
            @RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude) throws InterruptedException {
        log.info(String.format("Входящий запрос температуры с параметрами: longitude = %s, latitude = %s", longitude, latitude));
//        TimeUnit.SECONDS.sleep(10); // Эмуляция долгой работы сервиса
        return ResponseEntity.ok(new WeatherRs(LocalDateTime.now(), random.nextInt() + ""));
    }

    // Эндпоинт для проверки работы Circuit Breaker
    @GetMapping("/error")
    ResponseEntity<WeatherRs> getWeatherWithError(
            @RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude) {
        log.info(String.format("Входящий запрос температуры с параметрами: longitude = %s, latitude = %s", longitude, latitude));
        var status = longitude < 20D ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        ResponseEntity<WeatherRs> response = ResponseEntity.status(status).body(null);
        log.info(status.toString());
        return response;
    }
}
