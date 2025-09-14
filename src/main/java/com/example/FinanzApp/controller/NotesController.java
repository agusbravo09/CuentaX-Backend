package com.example.FinanzApp.controller;

import com.example.FinanzApp.dto.notes.NotesRequestDTO;
import com.example.FinanzApp.dto.notes.NotesResponseDTO;
import com.example.FinanzApp.service.INotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
public class NotesController {
    @Autowired
    private INotesService notesService;

    @PostMapping
    public ResponseEntity<NotesResponseDTO> createNote(@RequestBody NotesRequestDTO noteRequestDTO) {
        NotesResponseDTO response = notesService.createNote(noteRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotesResponseDTO> getNoteById(@PathVariable Long id) {
        NotesResponseDTO note = notesService.getNoteById(id);
        return ResponseEntity.ok(note);
    }

    @GetMapping
    public ResponseEntity<List<NotesResponseDTO>> getAllNotes() {
        List<NotesResponseDTO> notes = notesService.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotesResponseDTO>> getNotesByUser(@PathVariable Long userId) {
        List<NotesResponseDTO> notes = notesService.getNotesByUser(userId);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/assigned/{asignToId}")
    public ResponseEntity<List<NotesResponseDTO>> getNotesAssignedToUser(@PathVariable Long asignToId) {
        List<NotesResponseDTO> notes = notesService.getNotesAssignedToUser(asignToId);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<NotesResponseDTO>> getTasks(@RequestParam Boolean task) {
        List<NotesResponseDTO> notes = notesService.getTasks(task);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/user/{userId}/tasks")
    public ResponseEntity<List<NotesResponseDTO>> getTasksByUser(
            @PathVariable Long userId,
            @RequestParam Boolean task) {
        List<NotesResponseDTO> notes = notesService.getTasksByUser(userId, task);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<NotesResponseDTO>> searchByTitle(@RequestParam String title) {
        List<NotesResponseDTO> notes = notesService.searchByTitle(title);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/search/content")
    public ResponseEntity<List<NotesResponseDTO>> searchByContent(@RequestParam String content) {
        List<NotesResponseDTO> notes = notesService.searchByContent(content);
        return ResponseEntity.ok(notes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotesResponseDTO> updateNote(
            @PathVariable Long id,
            @RequestBody NotesRequestDTO noteRequestDTO) {
        NotesResponseDTO updatedNote = notesService.updateNote(id, noteRequestDTO);
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        notesService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<NotesResponseDTO> addComment(
            @PathVariable Long id,
            @RequestParam String comment) {
        NotesResponseDTO updatedNote = notesService.addComment(id, comment);
        return ResponseEntity.ok(updatedNote);
    }

    // Endpoint específico para organización
    @GetMapping("/organization/{userId}")
    public ResponseEntity<List<NotesResponseDTO>> getNotesForOrganization(
            @PathVariable Long userId,
            @RequestParam List<Long> organizationUserIds) {
        List<NotesResponseDTO> notes = notesService.getNotesForOrganization(userId, organizationUserIds);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("/{noteId}/assign/{asignToId}")
    public ResponseEntity<NotesResponseDTO> assignNoteToOrganizationMember(
            @PathVariable Long noteId,
            @PathVariable Long asignToId,
            @RequestParam Long currentUserId) {
        NotesResponseDTO updatedNote = notesService.assignNoteToOrganizationMember(noteId, asignToId, currentUserId);
        return ResponseEntity.ok(updatedNote);
    }
}
