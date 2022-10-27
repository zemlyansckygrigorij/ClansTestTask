package com.example.clanstesttask.service;

import com.example.clanstesttask.entity.User;
import com.example.clanstesttask.repo.ClanService;
import com.example.clanstesttask.repo.UserService;
import com.example.clanstesttask.service.userservice.UserAddGoldService;
import com.example.clanstesttask.service.userservice.UserSubtractGoldService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.clanstesttask.entity.Clan;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@Transactional
class UserSubtractGoldServiceTest {
    @Autowired
    UserSubtractGoldService usImpl ;
    @Autowired
    UserAddGoldService userAddGoldServicesImpl ;
    @Autowired
    UserService uService;
    @Autowired
    ClanService csImpl ;

    @DisplayName("Проверка удалеления золота у клану пользователем")
    @Test
    void subtractGoldToClan() throws Exception {
        Clan clan = csImpl.getClan(2);
        User user = uService.getUser(2);
        userAddGoldServicesImpl.addGoldToClan(2,2,10);
        assertEquals(clan.getGold()+10,csImpl.getClan(2).getGold());
        assertEquals(user.getGold() - 10,uService.getUser(2).getGold() );
        usImpl.subtractGoldToClan(2,2,4);
        assertEquals(clan.getGold()+6,csImpl.getClan(2).getGold());
        assertEquals(user.getGold() - 6,uService.getUser(2).getGold() );
        Exception thrown = assertThrows(Exception.class, () -> {
            usImpl.subtractGoldToClan(2,2,4000);
        }, "Exception was expected");

        assertEquals(clan.getGold()+6,csImpl.getClan(2).getGold());
    }
}