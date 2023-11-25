package ru.venidiktov.weather.entity;

import java.time.LocalDateTime;
import ru.venidiktov.weather.entity.enums.TemperatureType;

public record WeatherModel(LocalDateTime date, Double temperature, TemperatureType type) {
}
