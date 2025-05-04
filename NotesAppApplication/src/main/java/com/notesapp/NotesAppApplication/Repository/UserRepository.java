package com.notesapp.NotesAppApplication.Repository;
import com.notesapp.NotesAppApplication.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // CRUD Operations

    public boolean userExists(String username) {
        String sql = "SELECT COUNT(*) FROM test_db.user_s WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO test_db.user_s (username, password, email) VALUES (?, ?, ?)",
                user.getUsername(), user.getPassword(), user.getEmail());
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE test_db.user_s SET username=?, password=?, email=? WHERE id=?",
                user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM test_db.user_s WHERE id=?", id);
    }

    public User findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM test_db.user_s WHERE id=?",
                new BeanPropertyRowMapper<>(User.class), id);
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM test_db.user_s",
                new BeanPropertyRowMapper<>(User.class));
    }



//    public User loginUser(String username, String password) {
//        String sql = "SELECT * FROM test_db.users WHERE username = ? AND password = ?";
//        List<User> users = jdbcTemplate.query(sql, (rs, rowNum) -> {
//            User user = new User();
//            user.setId(rs.getInt("id"));
//            user.setUsername(rs.getString("username"));
//            user.setPassword(rs.getString("password"));
//            return user;
//        }, username, password);
//
//        return users.isEmpty() ? null : users.get(0);
//    }



}
