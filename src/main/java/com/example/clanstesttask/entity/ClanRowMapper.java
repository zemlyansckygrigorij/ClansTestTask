package com.example.clanstesttask.entity;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClanRowMapper implements RowMapper<Clan> {

    @Override
    public Clan mapRow(ResultSet resultSet, int i) throws SQLException {
        Clan clan = new Clan();
        clan.setId(resultSet.getLong("id"));
        clan.setName(resultSet.getString("name"));
        clan.setGold(resultSet.getInt("gold"));

        return clan;
    }
}
