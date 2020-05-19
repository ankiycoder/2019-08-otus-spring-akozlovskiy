package ru.akozlovskiy.springdz16.health;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DbAvailableHealthIndicator implements HealthIndicator {

	@Autowired
	private DataSource dataSource;

	@Override
	public Health health() {
		try (Connection connection = dataSource.getConnection()) {
		} catch (SQLException e) {
			return Health.down().withDetail("DB is not available", e.getMessage()).build();
		}
		return Health.up().build();
	}
}