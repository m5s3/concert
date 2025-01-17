package com.concert;

import com.concert.config.TestContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestContainerConfig.class)
class ConcertApplicationTests {

    @Test
    void contextLoads() {
    }

}
