package com.example.RestTask.Repository;

import com.example.RestTask.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM note WHERE id = :id")
    Note getNote(Long id);
}
