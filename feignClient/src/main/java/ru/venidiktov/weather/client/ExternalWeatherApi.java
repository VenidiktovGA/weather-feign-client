package ru.venidiktov.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.venidiktov.weather.model.WeatherRs;

/**
 * Реализацию "ручек" и их вызовов мы делегируем на OpenFeignClient,
 * OpenFeingClient возьмет на себя реализацию методов которые будут отвечать за те или иные "ручки"
 */

/**
 * В аннотации @FeingClient можно указать url на который пойдут запросы,
 * но это будет не гибко, поэтому мы его укажем в application.yml
 */
@FeignClient(name = "weather") // Имя обязательно!
public interface ExternalWeatherApi {

    /**
     * Из данного метода аннотаций и его сигнатуры, feign client сформирует готовый метод,
     * вызвав котоырй мы обратися к внешнему сервису (Конкретно в нашем случае к контроллеру WeatherController из externalWeatherSource модуля)
     */
    @GetMapping
    WeatherRs getWeather(
            @RequestParam(name = "longitude") double longitude,
            @RequestParam(name = "latitude") double latitude
    );
}
