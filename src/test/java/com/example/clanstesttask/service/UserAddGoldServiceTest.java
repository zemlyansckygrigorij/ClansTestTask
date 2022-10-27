package com.example.clanstesttask.service;

import com.example.clanstesttask.entity.User;
import com.example.clanstesttask.repo.ClanService;
import com.example.clanstesttask.repo.UserService;
import com.example.clanstesttask.service.userservice.UserAddGoldService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.clanstesttask.entity.Clan;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@Transactional
class UserAddGoldServiceTest {
    @Autowired
    UserAddGoldService usAdd ;
    @Autowired
    ClanService csImpl ;
    @Autowired
    UserService uService;

    @DisplayName(" Проверка добавления золота клану пользователем")
    @Test
    void addGoldToClan() throws Exception {
        Clan clan = csImpl.getClan(2);
        User user = uService.getUser(2);
        usAdd.addGoldToClan(2,2,9);
        assertEquals(clan.getGold()+9,csImpl.getClan(2).getGold());
        assertEquals(user.getGold() - 9,uService.getUser(2).getGold() );
        Exception thrown = assertThrows(Exception.class, () -> {
            usAdd.addGoldToClan(2,2,9000);
        }, "Exception was expected");
        assertEquals(user.getGold() - 9,uService.getUser(2).getGold() );
        assertEquals(clan.getGold()+9,csImpl.getClan(2).getGold());
    }
}