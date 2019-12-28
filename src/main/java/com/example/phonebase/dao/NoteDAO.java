package com.example.phonebase.dao;


import com.example.phonebase.model.Note;
import com.example.phonebase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NoteDAO extends JpaRepository<Note, Integer> {
    @Modifying
    @Query("DELETE FROM Note u WHERE u.id=:id")
    int delete(@Param("id") int id);
}
