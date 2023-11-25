package ru.venidiktov.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Аннотация @EnableFeignClients определяет клиента (класс) для http который будет
 * использовать feign client, так как не все протоколы транспортного уровня поддерживают
 * некоторые виды запросов, например FETCH
 */
@EnableFeignClients // Включаем feign client
@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

}
