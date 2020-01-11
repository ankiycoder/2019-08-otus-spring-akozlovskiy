package ru.akozlovskiy.springdz08.domain.repository;

<<<<<<< HEAD
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@ComponentScan({ "ru.akozlovskiy.springdz08"})
=======
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.akozlovskiy.springdz08.config", "ru.akozlovskiy.springdz08.domain.repository"})

>>>>>>> 207a043bde0d90ca0dc1ffd0239fbbf1e366848b
public abstract class AbstractRepositoryTest {
}
