package com.example.phonebase.util;

import com.example.phonebase.model.Note;
import com.example.phonebase.to.NoteTo;


public class NoteUtil {
    public static Note createNewFromTo(NoteTo noteTo){
        return new Note(null,noteTo.getName(),noteTo.getNumber(),noteTo.getComment());
    }
    public static Note updateFromTo(Note note, NoteTo noteTo){
        note.setName(noteTo.getName());
        note.setNumber(noteTo.getNumber());
        note.setComment(noteTo.getComment());
        return note;
    }

    /* @ConstructorProperties({"id","name","number","comment"})*/
}
