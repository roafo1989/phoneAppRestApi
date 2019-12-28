package com.example.phonebase.service;

import com.example.phonebase.dao.NoteDAO;
import com.example.phonebase.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;

import static com.example.phonebase.util.ValidationUtil.checkNotFound;
import static com.example.phonebase.util.ValidationUtil.checkNotFoundWithId;


@Service
public class NoteService {
    private final NoteDAO repos;
    @Autowired
    public NoteService(NoteDAO repos) {
        this.repos = repos;
    }


    public List<Note> getAll() {
        return repos.findAll();
    }

    public Note getByNumber(String number) {
        Assert.notNull(number,"number must be not null");
        return checkNotFound(repos.findNoteByNumber(number),"number: ");
    }

    public Note create(Note note) {
        Assert.notNull(note, "note must not be null");
        return repos.save(note);
    }

    public void update(Note note) {
        Assert.notNull(note, "note must not be null");
        checkNotFoundWithId(repos.save(note), note.getId());
    }



    public void delete(int id) {
        checkNotFoundWithId(repos.delete(id), id);
    }

    public Note getById(int id) {
        return repos.findById(id).orElse(null);
    }

}
