package ru.akozlovskiy.springdz14.domain.repository;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@ComponentScan({ "ru.akozlovskiy.springdz08" })
public abstract class AbstractRepositoryTest {
}
