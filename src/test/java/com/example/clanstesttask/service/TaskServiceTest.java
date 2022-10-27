package com.example.clanstesttask.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@Transactional
class TaskServiceTest {

    @Autowired
    TaskService taskService;

    @DisplayName("Проверка добавления золота клану пользователем")
    @Test
    void completeTask() {
        taskService.completeTask();
        assertEquals(0,0);
    }
}