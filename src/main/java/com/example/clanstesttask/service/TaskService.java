package com.example.clanstesttask.service;

import com.example.clanstesttask.entity.Clan;
import com.example.clanstesttask.entity.ThreadUser;
import com.example.clanstesttask.entity.User;
import com.example.clanstesttask.repo.ClanService;
import com.example.clanstesttask.repo.UserService;
import com.example.clanstesttask.service.userservice.UserAddGoldService;
import com.example.clanstesttask.service.userservice.UserSubtractGoldService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final ClanService clanService;
    private final UserService userService;
    private final UserAddGoldService userAddGoldService;
    private final UserSubtractGoldService userSubtractGoldService;
    private List<User> users;
    private List<Clan> clans;

    public void completeTask() {
        users = userService.getAllUser();
        clans = clanService.getAllClan();
        users.stream().forEach((user) -> {
            ThreadUser tUser = new ThreadUser(user,clans);
            tUser.setUserAddGoldService(userAddGoldService);
            tUser.setUserSubtractGoldService(userSubtractGoldService);
            tUser.run();
        });
    }
}

