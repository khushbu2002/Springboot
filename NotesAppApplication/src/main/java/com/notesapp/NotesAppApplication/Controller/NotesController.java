package com.notesapp.NotesAppApplication.Controller;


import com.notesapp.NotesAppApplication.Model.Notes;
import com.notesapp.NotesAppApplication.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @PostMapping("/create")
    public ResponseEntity<String> createNote(@RequestBody Notes note) {
        int result = notesService.createNote(note);
        return (result > 0) ?
                ResponseEntity.ok("Note created successfully") :
                ResponseEntity.internalServerError().body("Failed to create note");
    }
    // READ: Get note by ID
    @GetMapping("/get/{id}")
    public Notes getNoteById(@PathVariable int id) {
        return notesService.getNoteById(id);
    }

    // READ: Get all notes
    @GetMapping("/get/all")
    public List<Notes> getAllNotes() {
        return notesService.getAllNotes();
    }

    // READ: Get notes by username
    @GetMapping("/user/{username}")
    public List<Notes> getNotesByUsername(@PathVariable String username) {
        return notesService.getNotesByUsername(username);
    }

    // UPDATE note
    @PutMapping("/update")
    public ResponseEntity<String> updateNote(@RequestBody Notes note) {
        try {
            int rowsAffected = notesService.updateNote(note);
            if (rowsAffected > 0) {
                return ResponseEntity.ok("Note updated successfully!");
            } else {
                return ResponseEntity.status(404).body("Note not found or update failed.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // DELETE note
    @DeleteMapping("/delete/{id}")
    public String deleteNote(@PathVariable int id) {
        notesService.deleteNote(id);
        return "Note deleted successfully!";
    }
}
