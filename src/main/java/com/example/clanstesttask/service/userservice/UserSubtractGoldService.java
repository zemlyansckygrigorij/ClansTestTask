package com.example.clanstesttask.service.userservice;

import com.example.clanstesttask.repo.ClanService;
import com.example.clanstesttask.repo.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSubtractGoldService {
    private final ClanService clanService;
    private final UserService userService;
    public void subtractGoldToClan(long userId, long clanId, int gold) throws Exception {
        clanService.updateGold(clanId, -gold);
        userService.updateGold(userId,  gold);
    }
}
