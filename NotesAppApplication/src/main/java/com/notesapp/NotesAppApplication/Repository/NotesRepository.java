package com.notesapp.NotesAppApplication.Repository;

import com.notesapp.NotesAppApplication.Model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NotesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Notes mapRowToNote(ResultSet rs, int rowNum) throws SQLException {
        Notes note = new Notes();
        note.setId(rs.getInt("id"));
        note.setUsername(rs.getString("username")); // Comes from join
        note.setTitle(rs.getString("title"));
        note.setContent(rs.getString("content"));
        note.setCreated_on(rs.getTimestamp("created_on").toLocalDateTime());
        note.setUpdated_on(rs.getTimestamp("updated_on").toLocalDateTime());
        return note;
    }
    public int save(Notes note) {
        // Fetch user_id from username
        String getUserIdQuery = "SELECT id FROM test_db.user_s WHERE username = ?";
        Integer userId = jdbcTemplate.queryForObject(getUserIdQuery, Integer.class, note.getUsername());

        if (userId == null) {
            throw new RuntimeException("User not found with username: " + note.getUsername());
        }

        return jdbcTemplate.update("INSERT INTO test_db.notes (user_id, title, content) VALUES (?, ?, ?)",
                userId, note.getTitle(), note.getContent());
    }

    public List<Notes> findAll() {
        String query = "SELECT n.*, u.username FROM test_db.notes n JOIN test_db.user_s u ON n.user_id = u.id";
        return jdbcTemplate.query(query, this::mapRowToNote);
    }

    public Notes findById(int id) {
        String query = "SELECT n.*, u.username FROM test_db.notes n JOIN test_db.user_s u ON n.user_id = u.id WHERE n.id = ?";
        return jdbcTemplate.queryForObject(query, this::mapRowToNote, id);
    }

    public int update(Notes note) {
        String getUserIdQuery = "SELECT id FROM user_s WHERE username = ?";
        Integer userId = jdbcTemplate.queryForObject(getUserIdQuery, Integer.class, note.getUsername());

        if (userId == null) {
            throw new RuntimeException("User not found with username: " + note.getUsername());
        }

        // Case 1: Update using note ID
        if (note.getId() != 0) {
            return jdbcTemplate.update(
                    "UPDATE notes SET user_id = ?, title = ?, content = ?, updated_on = CURRENT_TIMESTAMP WHERE id = ?",
                    userId, note.getTitle(), note.getContent(), note.getId()
            );
        }

        // Case 2: Update first note found for the username
        String selectNoteIdQuery = "SELECT id FROM notes WHERE user_id = ? LIMIT 1";
        Integer noteId = jdbcTemplate.queryForObject(selectNoteIdQuery, Integer.class, userId);

        if (noteId == null) {
            throw new RuntimeException("No note found for username: " + note.getUsername());
        }

        return jdbcTemplate.update(
                "UPDATE notes SET user_id = ?, title = ?, content = ?, updated_on = CURRENT_TIMESTAMP WHERE id = ?",
                userId, note.getTitle(), note.getContent(), noteId
        );
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM test_db.notes WHERE id = ?", id);
    }

    public List<Notes> findByUsername(String username) {
        String query = """
            SELECT n.*, u.username FROM test_db.notes n 
            JOIN test_db.user_s u ON n.user_id = u.id 
            WHERE u.username = ?
        """;
        return jdbcTemplate.query(query, this::mapRowToNote, username);
    }

}
