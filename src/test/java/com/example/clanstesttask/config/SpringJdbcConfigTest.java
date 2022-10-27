package com.example.clanstesttask.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
class SpringJdbcConfigTest {
    @Autowired
    SpringJdbcConfig jdbcConfig;

    @DisplayName(" Проверка наличия объекта DataSource ")
    @Test
    void dataSource() {
        assertNotNull(jdbcConfig);
        assertNotNull(jdbcConfig.dataSource());
    }
}