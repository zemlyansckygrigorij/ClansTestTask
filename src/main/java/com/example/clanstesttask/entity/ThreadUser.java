package com.example.clanstesttask.entity;

import com.example.clanstesttask.service.userservice.UserAddGoldService;
import com.example.clanstesttask.service.userservice.UserSubtractGoldService;
import lombok.Setter;
import java.util.List;

@Setter
public class ThreadUser implements Runnable{
    private final User user;
    private final List<Clan> clans;
    private UserAddGoldService userAddGoldService;
    private UserSubtractGoldService userSubtractGoldService;

    public ThreadUser(User user, List<Clan> clans){
        this.user = user;
        this.clans = clans;
    }

    @Override
    public void run() {
        clans.stream().forEach((clan) ->{
            try {
                userAddGoldService.addGoldToClan(user.getId(),clan.getId(),10);
                userSubtractGoldService.subtractGoldToClan(user.getId(),clan.getId(),8);
                userAddGoldService.addGoldToClan(user.getId(),clan.getId(),20);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
