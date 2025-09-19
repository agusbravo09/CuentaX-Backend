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
        Notes note = notesMapper.toEntity(requestDTO);

        if(requestDTO.getUserId() != null) {
            User user = userRepo.findById(requestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found."));
            note.setUser(user);
        }

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
        note.setComments(requestDTO.getComments());

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

}
