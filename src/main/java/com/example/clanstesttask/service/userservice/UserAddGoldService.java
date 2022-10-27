package com.example.clanstesttask.service.userservice;

import com.example.clanstesttask.repo.ClanService;
import com.example.clanstesttask.repo.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAddGoldService {
    private final ClanService clanService;
    private final UserService userService;
    public void addGoldToClan(long userId, long clanId, int gold) throws Exception {
        userService.updateGold(userId,  -gold);
        clanService.updateGold(clanId, gold);
    }
}
