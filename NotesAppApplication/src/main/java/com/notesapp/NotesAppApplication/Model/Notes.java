package com.notesapp.NotesAppApplication.Model;

import java.time.LocalDateTime;

public class Notes {

    private int id;
    private String username;
    private String title;
    private String content;
    private LocalDateTime created_on;
    private LocalDateTime updated_on;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDateTime created_on) {
        this.created_on = created_on;
    }

    public LocalDateTime getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(LocalDateTime updated_on) {
        this.updated_on = updated_on;
    }

    public Notes() {
    }

    public Notes(int id, String username, String title, String content, LocalDateTime created_on, LocalDateTime updated_on) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.created_on = created_on;
        this.updated_on = updated_on;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created_on=" + created_on +
                ", updated_on=" + updated_on +
                '}';
    }
}
