package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.notes.NotesRequestDTO;
import com.example.FinanzApp.dto.notes.NotesResponseDTO;

import java.util.List;

public interface INotesService {
    NotesResponseDTO createNote(NotesRequestDTO requestDTO);
    NotesResponseDTO getNoteById(Long id);
    List<NotesResponseDTO> getAllNotes();
    List<NotesResponseDTO> getNotesByUser(Long userId);
    List<NotesResponseDTO> getNotesAssignedToUser(Long asignToId);
    List<NotesResponseDTO> getTasks(Boolean task);
    List<NotesResponseDTO> getTasksByUser(Long userId, Boolean task);
    List<NotesResponseDTO> searchByTitle(String title);
    List<NotesResponseDTO> searchByContent(String content);
    NotesResponseDTO updateNote(Long id, NotesRequestDTO requestDTO);
    void deleteNote(Long id);
    NotesResponseDTO addComment(Long id, String comment);

    //metodos para organizacion
    List<NotesResponseDTO> getNotesForOrganization(Long userId, List<Long> organizationUserIds);
    NotesResponseDTO assignNoteToOrganizationMember(Long noteId, Long asignToId, Long currentUserId);
}
