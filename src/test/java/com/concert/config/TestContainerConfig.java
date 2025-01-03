package com.concert.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

@TestConfiguration
@ActiveProfiles("test")
public class TestContainerConfig {

    private static final MySQLContainer<?> mysql;
    private static final int MYSQL_PORT = 53306;  // 원하는 포트 번호

    static {
        mysql = new MySQLContainer<>("mysql:8.0")
                .withDatabaseName("concert_test")
                .withUsername("sa")
                .withPassword("1234")
                .withInitScript("db/schema.sql");  // 스키마 먼저 생성

        mysql.setPortBindings(List.of(MYSQL_PORT + ":3306"));
        mysql.start();

        System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
        System.setProperty("spring.datasource.username", mysql.getUsername());
        System.setProperty("spring.datasource.password", mysql.getPassword());
    }
}
