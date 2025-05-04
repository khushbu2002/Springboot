package com.notesapp.NotesAppApplication.Service;

import com.notesapp.NotesAppApplication.Model.Notes;
import com.notesapp.NotesAppApplication.Repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    public int createNote(Notes note) {
        return notesRepository.save(note);
    }

    public Notes getNoteById(int id) {
        return notesRepository.findById(id);
    }

    public List<Notes> getAllNotes() {
        return notesRepository.findAll();
    }

    public List<Notes> getNotesByUsername(String username) {
        return notesRepository.findByUsername(username);
    }

    public int updateNote(Notes note) {
        return notesRepository.update(note);
    }

    public int deleteNote(int id) {
        return notesRepository.delete(id);
    }
}
