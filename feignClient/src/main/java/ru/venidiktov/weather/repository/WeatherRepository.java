package ru.venidiktov.weather.repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.venidiktov.weather.entity.WeatherModel;
import ru.venidiktov.weather.entity.enums.TemperatureType;

@Slf4j
@Repository
public class WeatherRepository {

    private final Random random = new Random();

    public Optional<WeatherModel> getWeather(final Double longitude, final Double latitude, final LocalDateTime date) {
        /**
         * Проверкой longitude > 20D && latitude > 20D мы моделируем ситуацию что в репозитории есть данные только для некоторых локаций,
         * за температурой в других местах нужно идти в другой сервис (Интеграция с внешним сервисом!)
         */
        log.info(String.format(
                "Поиск во внутреннем хранилище данных о погоде по параметрам longitude = %s, latitude = %s, date = %s",
                longitude, latitude, date
        ));
        if (longitude > 20D && latitude > 20D) {
            log.info(String.format(
                    "Найдены данные о погоде во внутреннем хранилище по параметрам longitude = %s, latitude = %s, date = %s",
                    longitude, latitude, date
            ));
            return Optional.of(new WeatherModel(date.truncatedTo(ChronoUnit.HOURS), random.nextDouble(40d), TemperatureType.C));
        } else {
            log.info(String.format(
                    "Не найдены данные о погоде во внутреннем хранилище по параметрам longitude = %s, latitude = %s, date = %s",
                    longitude, latitude, date
            ));
            return Optional.empty();
        }
    }
}
