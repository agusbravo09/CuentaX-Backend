package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByUserId(Long userId);
    List<Notes> findByAsignToId(Long asignToId);
    List<Notes> findByTask(Boolean task);
    List<Notes> findByUserIdAndTask(Long userId, Boolean task);
    List<Notes> findByAsignToIdAndTask(Long asignToId, Boolean task);
    List<Notes> findByTitleContainingIgnoreCase(String title);
    List<Notes> findByContentContainingIgnoreCase(String content);

    //Validar si el usuario pertenece a la misma org.
    List<Notes> findByUserIdAndAsignToIdIn(Long userId, List<Long> organizationUserIds);
}
