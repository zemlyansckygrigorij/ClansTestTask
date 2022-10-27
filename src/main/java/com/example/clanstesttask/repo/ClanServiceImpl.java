package com.example.clanstesttask.repo;

import com.example.clanstesttask.entity.Clan;
import com.example.clanstesttask.entity.ClanRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class ClanServiceImpl implements ClanService  {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    private final String SQL_FIND_BY_ID="SELECT * FROM clans WHERE id = ?";
    private final String SQL_GET_ALL="SELECT * FROM clans";

    private final String SQL_UPDATE="UPDATE clans SET " +
            "gold =? WHERE id = ?";
    private final String SQL_INSERT="INSERT INTO clans" +
            "(id,name, gold)" +
            " VALUES(nextval('clan_sequence'),?,? )";

    @Autowired
    public ClanServiceImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("clans").
                usingGeneratedKeyColumns("id");
    }

    @Override
    public Clan createClan(Clan clan) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, clan.getName());
                ps.setInt(2, clan.getGold());

                return ps;
            }
        }, keyHolder);

       int key = Integer.parseInt(keyHolder.getKeyList().get(0).get("id").toString());
        clan.setId(key);
        return clan;
    }

    @Override
    public Clan getClan(long clanId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new ClanRowMapper(), clanId);
    }

    @Override
    public List<Clan> getAllClan() {
        return jdbcTemplate.query(SQL_GET_ALL, new ClanRowMapper());
    }


    @Override
    public boolean  updateGold(long clanId, int gold) throws Exception {
        Clan clan = getClan(clanId);
        int newGold = clan.getGold()+gold;
        if(newGold<0) throw new Exception("Clan has not enough gold");
        return jdbcTemplate.update(SQL_UPDATE, newGold,clanId ) > 0;
    }
}
