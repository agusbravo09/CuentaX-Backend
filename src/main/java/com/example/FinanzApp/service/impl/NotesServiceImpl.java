package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.notes.NotesRequestDTO;
import com.example.FinanzApp.dto.notes.NotesResponseDTO;
import com.example.FinanzApp.mappers.NotesMapper;
import com.example.FinanzApp.model.Notes;
import com.example.FinanzApp.model.User;
import com.example.FinanzApp.repository.INotesRepository;
import com.example.FinanzApp.repository.IUserRepository;
import com.example.FinanzApp.service.INotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotesServiceImpl implements INotesService {
    @Autowired
    private INotesRepository notesRepo;
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private NotesMapper notesMapper;

    @Override
    @Transactional
    public NotesResponseDTO createNote(NotesRequestDTO requestDTO) {
        User user = userRepo.findById(requestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found."));

        User asignTo = null;
        if(requestDTO.getAsignToId() != null){
            asignTo = userRepo.findById(requestDTO.getAsignToId()).orElseThrow(() -> new RuntimeException("Assigned user not found."));
        }

        Notes note = notesMapper.toEntity(requestDTO);
        note.setUser(user);
        note.setAsignTo(asignTo);
        note.setCreatedDate(LocalDateTime.now());

        Notes savedNote = notesRepo.save(note);

        return notesMapper.toResponse(savedNote);
    }

    @Override
    public NotesResponseDTO getNoteById(Long id) {
        Notes note = notesRepo.findById(id).orElseThrow(() -> new RuntimeException("Note not found."));
        return notesMapper.toResponse(note);
    }

    @Override
    public List<NotesResponseDTO> getAllNotes() {
        return notesRepo.findAll().stream().map(notesMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<NotesResponseDTO> getNotesByUser(Long userId) {
        return notesRepo.findByUserId(userId).stream().map(notesMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<NotesResponseDTO> getNotesAssignedToUser(Long asignToId) {
        return notesRepo.findByAsignToId(asignToId).stream().map(notesMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<NotesResponseDTO> getTasks(Boolean task) {
        return notesRepo.findByTask(task).stream().map(notesMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<NotesResponseDTO> getTasksByUser(Long userId, Boolean task) {
        return notesRepo.findByUserIdAndTask(userId, task).stream().map(notesMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<NotesResponseDTO> searchByTitle(String title) {
        return notesRepo.findByTitleContainingIgnoreCase(title).stream().map(notesMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<NotesResponseDTO> searchByContent(String content) {
        return notesRepo.findByContentContainingIgnoreCase(content).stream().map(notesMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NotesResponseDTO updateNote(Long id, NotesRequestDTO requestDTO) {
        Notes note = notesRepo.findById(id).orElseThrow(() -> new RuntimeException("Note not found."));

        note.setTitle(requestDTO.getTitle());
        note.setContent(requestDTO.getContent());
        note.setTask(requestDTO.getTask());
        note.setComments(requestDTO.getComments());

        if(requestDTO.getAsignToId() != null){
            User asignTo = userRepo.findById(requestDTO.getAsignToId()).orElseThrow(() -> new RuntimeException("User not found"));
            note.setAsignTo(asignTo);
        }else{
            note.setAsignTo(null);
        }

        Notes updatedNote = notesRepo.save(note);
        return notesMapper.toResponse(updatedNote);
    }

    @Override
    @Transactional
    public void deleteNote(Long id) {
        if(!notesRepo.existsById(id)){
            throw new RuntimeException("Note not found.");
        }
        notesRepo.deleteById(id);
    }

    @Override
    @Transactional
    public NotesResponseDTO addComment(Long id, String comment) {
        Notes note = notesRepo.findById(id).orElseThrow(() -> new RuntimeException("Note not found."));

        String currentComment = note.getComments() != null ? note.getComments() : "";
        note.setComments(currentComment + comment + "\n");

        Notes updatedNote = notesRepo.save(note);
        return notesMapper.toResponse(updatedNote);
    }

    @Override
    public List<NotesResponseDTO> getNotesForOrganization(Long userId, List<Long> organizationUserIds) {
        return notesRepo.findByUserIdAndAsignToIdIn(userId, organizationUserIds).stream().map(notesMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NotesResponseDTO assignNoteToOrganizationMember(Long noteId, Long asignToId, Long currentUserId) {
        Notes note = notesRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found."));

        User asignTo = userRepo.findById(asignToId).orElseThrow(() -> new RuntimeException("User not found."));

        note.setAsignTo(asignTo);
        Notes updatedNote = notesRepo.save(note);

        return notesMapper.toResponse(updatedNote);
    }
}
