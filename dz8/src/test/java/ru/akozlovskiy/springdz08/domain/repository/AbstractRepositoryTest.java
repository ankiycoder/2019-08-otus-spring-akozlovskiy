package ru.akozlovskiy.springdz08.domain.repository;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.akozlovskiy.springdz08.config", "ru.akozlovskiy.springdz08.domain.repository"})

public abstract class AbstractRepositoryTest {
}
