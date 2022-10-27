package com.example.clanstesttask.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.example.clanstesttask.entity.Clan;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@Transactional
class ClanServiceImplTest {
    @Autowired
    ClanServiceImpl csImpl ;

    @DisplayName("1. Проверка создания обьекта клан")
    @Test
    void createClan() {
        Clan clan = new Clan();
        clan.setName("testClan1");
        clan.setGold(2);
        Clan clanNew = csImpl.createClan( clan);
        assertEquals( clanNew.getName() , clan.getName());
        assertEquals( clanNew.getGold() , clan.getGold());
    }

    @DisplayName("2. Проверка получения клана из базы данных.")
    @Test
    void getClan() {
        Clan clan = csImpl.getClan(2);
        assertEquals( clan.getName() , "Amaranth Legion");
        assertEquals( clan.getGold() , 0);
    }

    @DisplayName("3. Проверка обновления количества золота у клана.")
    @Test
    void updateGold() throws Exception {
        Clan clan = csImpl.getClan(2);
        int gold = clan.getGold();
        csImpl.updateGold(2,4);
        assertEquals(gold +4  , csImpl.getClan(2).getGold());

        Exception thrown = assertThrows(Exception.class, () -> {
            csImpl.updateGold(2,-(gold+100));
        }, "Exception was expected");
    }

    @DisplayName("4. Проверка получения всех кланов")
    @Test
    void getAllClan() {
        List<Clan> clans = csImpl.getAllClan();
        assertEquals( clans.size() , 6);
    }
}