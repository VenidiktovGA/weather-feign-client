package ru.venidiktov.weather.model;

import java.time.LocalDateTime;

public record WeatherRs(LocalDateTime date, String temperature) {

    public static WeatherRs empty() {
        return new WeatherRs(LocalDateTime.now(), "no data found");
    }
}
