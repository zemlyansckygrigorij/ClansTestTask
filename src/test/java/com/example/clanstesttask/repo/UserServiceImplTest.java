package com.example.clanstesttask.repo;

import com.example.clanstesttask.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@Transactional
class UserServiceImplTest {
    @Autowired
    UserService usImpl ;

    @DisplayName("1. Проверка получения обьекта User")
    @Test
    void getUser() {
        User user = usImpl.getUser(2);
        assertEquals( user.getName() , "мэйн");
        assertEquals( user.getGold() , 4000);
    }

    @DisplayName("2. Проверка обновления количества золота у User.")
    @Test
    void updateGold() throws Exception {
        User user = usImpl.getUser(2);
        int gold = user.getGold();
        usImpl.updateGold(2,4);
        assertEquals(gold +4  ,usImpl.getUser(2).getGold());

        Exception thrown = assertThrows(Exception.class, () -> {
            usImpl.updateGold(2,-(gold+100));
        }, "Exception was expected");
    }
}