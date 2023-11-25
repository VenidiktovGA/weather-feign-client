package ru.venidiktov.model;

import java.time.LocalDateTime;

public record WeatherRs(LocalDateTime date, String temperature) {
}
