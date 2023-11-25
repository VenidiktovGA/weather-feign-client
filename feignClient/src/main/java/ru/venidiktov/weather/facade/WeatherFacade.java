package ru.venidiktov.weather.facade;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.venidiktov.weather.client.ExternalWeatherApi;
import ru.venidiktov.weather.model.WeatherRq;
import ru.venidiktov.weather.model.WeatherRs;
import ru.venidiktov.weather.service.WeatherService;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherFacade {

    private final WeatherService weatherService;
    private final ExternalWeatherApi externalWeatherApi;

    public WeatherRs getWeather(WeatherRq request) {
        log.info("Поиск температуры в сервисе по параметрам: " + request);
        return weatherService.getWeather(request.longitude(), request.latitude(), request.date())
                .map(weatherModel ->
                        new WeatherRs(weatherModel.date(), weatherModel.temperature() + weatherModel.type().toString())
                ).orElseGet(() -> getExternalWeatherApi(request.longitude(), request.latitude()));
    }

    private WeatherRs getExternalWeatherApi(Double longitude, Double latitude) {
        log.info(String.format("Запрос температуры во внешний сервис с параметрами: longitude = %s, latitude = %s", longitude, latitude));
        return
                Optional.ofNullable(
                        externalWeatherApi.getWeather(longitude, latitude)
                ).map(rs -> new WeatherRs(rs.date(), rs.temperature())).orElse(WeatherRs.empty()); // TODO: 11/24/2023 Можно конечно тут кинуть exception и в ExceptionHandler его обработать!
    }
}
