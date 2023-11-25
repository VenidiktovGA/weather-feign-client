package ru.venidiktov.weather.model;

import java.time.LocalDateTime;

public record WeatherRq(Double longitude, Double latitude, LocalDateTime date) {
}
