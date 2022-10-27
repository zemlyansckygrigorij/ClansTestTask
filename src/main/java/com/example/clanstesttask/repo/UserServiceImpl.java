package com.example.clanstesttask.repo;

import com.example.clanstesttask.entity.User;
import com.example.clanstesttask.entity.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    private final String SQL_FIND_BY_ID="SELECT * FROM users WHERE id = ?";
    private final String SQL_UPDATE="UPDATE users SET " +
            "gold =? WHERE id = ?";
    private final String SQL_GET_ALL="SELECT * FROM users";

    @Autowired
    public UserServiceImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("users").
                usingGeneratedKeyColumns("id");
    }

    public User getUser(long userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new UserRowMapper(), userId);
    }

    @Override
    public List<User> getAllUser() {
        return jdbcTemplate.query(SQL_GET_ALL, new UserRowMapper());
    }

    @Override
    public boolean updateGold(long userId, int gold) throws Exception {
        User user = getUser(userId);
        int newGold = user.getGold()+gold;
        if(newGold<0) throw new Exception("User has not enough gold");
        return jdbcTemplate.update(SQL_UPDATE, newGold,userId ) > 0;
    }
}
