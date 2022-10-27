package com.example.clanstesttask.repo;

import com.example.clanstesttask.entity.Clan;
import java.util.List;

public interface ClanService {
    Clan getClan(long clanId);
    List<Clan> getAllClan();
    boolean  updateGold(long clanId, int gold) throws Exception;
    Clan createClan(Clan clan);
}
