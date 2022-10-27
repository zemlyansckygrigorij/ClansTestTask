package com.example.clanstesttask.repo;

import com.example.clanstesttask.entity.User;
import java.util.List;

public interface UserService {
    User getUser(long userId);
    List<User> getAllUser();
    boolean  updateGold(long userId, int gold) throws Exception;
}
