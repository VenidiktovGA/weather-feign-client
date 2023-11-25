package ru.venidiktov.weather.repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Repository;
import ru.venidiktov.weather.entity.WeatherModel;
import ru.venidiktov.weather.entity.enums.TemperatureType;

@Repository
public class WeatherRepository {

    private final Random random = new Random();

    public Optional<WeatherModel> getWeather(final Double longitude, final Double latitude, final LocalDateTime date) {
        /**
         * Проверкой longitude > 20D && latitude > 20D мы моделируем ситуацию что в репозитории есть данные только для некоторых локаций,
         * за температурой в других местах нужно идти в другой сервис (Интеграция с внешним сервисом!)
         */
        if (longitude > 20D && latitude > 20D) {
            return Optional.of(new WeatherModel(date.truncatedTo(ChronoUnit.HOURS), random.nextDouble(40d), TemperatureType.C));
        } else {
            return Optional.empty();
        }
    }
}
