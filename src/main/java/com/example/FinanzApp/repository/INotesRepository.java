package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByUserId(Long userId);
    List<Notes> findByTitleContainingIgnoreCase(String title);
    List<Notes> findByContentContainingIgnoreCase(String content);
}
