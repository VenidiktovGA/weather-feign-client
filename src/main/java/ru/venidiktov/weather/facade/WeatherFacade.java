package ru.venidiktov.weather.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.venidiktov.weather.model.WeatherRq;
import ru.venidiktov.weather.model.WeatherRs;
import ru.venidiktov.weather.service.WeatherService;

@Component
@RequiredArgsConstructor
public class WeatherFacade {

    private final WeatherService weatherService;

    public WeatherRs getWeather(WeatherRq request) {
        return weatherService.getWeather(request.longitude(), request.latitude(), request.date())
                .map(weatherModel ->
                        new WeatherRs(weatherModel.date(), weatherModel.temperature() + weatherModel.type().toString())
                ).orElse(WeatherRs.empty()); // TODO: 11/24/2023 Можно конечно тут кинуть exception и в ExceptionHandler его обработать!
    }
}
