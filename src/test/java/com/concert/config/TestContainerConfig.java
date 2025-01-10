package com.concert.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

@TestConfiguration
@ActiveProfiles("test")
public class TestContainerConfig {

    private static final MySQLContainer<?> mysql;
    private static final GenericContainer<?> redis;

    private static final int MYSQL_PORT = 53306;  // 원하는 포트 번호
    private static final int REDIS_PORT = 63379;

    static {
        // MySQL 컨테이너 설정
        mysql = new MySQLContainer<>("mysql:8.0")
                .withDatabaseName("concert_test")
                .withUsername("sa")
                .withPassword("1234")
                .withInitScript("db/schema.sql");  // 스키마 먼저 생성

        mysql.setPortBindings(List.of(MYSQL_PORT + ":3306"));
        mysql.start();

        // Redis 컨테이너 설정
        redis = new GenericContainer<>("redis:7.0")
                .withExposedPorts(6379);

        redis.setPortBindings(List.of(REDIS_PORT + ":6379"));
        redis.start();

        // MySQL 환경변수 설정
        System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
        System.setProperty("spring.datasource.username", mysql.getUsername());
        System.setProperty("spring.datasource.password", mysql.getPassword());


        // Redis 환경변수 설정
        System.setProperty("spring.data.redis.host", redis.getHost());
        System.setProperty("spring.data.redis.port", redis.getMappedPort(6379).toString());
    }
}
