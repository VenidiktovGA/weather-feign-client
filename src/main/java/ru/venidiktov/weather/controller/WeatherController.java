package ru.venidiktov.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.venidiktov.weather.facade.WeatherFacade;
import ru.venidiktov.weather.model.WeatherRq;
import ru.venidiktov.weather.model.WeatherRs;

/**
 * Есть два вида контроллеров:
 *
 * @Controller - в нем можно отдавать обработанные шаблонизаторами (thymeleaf) html страницы так называемое представление
 * @RestController - отдает в ответ объекты представленные в некотором формате чаще всего JSON, для этого в себе имеет аннотацию @ResponseBody
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherFacade weatherFacade;

    @PostMapping
    public WeatherRs getWeather(@RequestBody final WeatherRq request) {
        return weatherFacade.getWeather(request);
    }
}
