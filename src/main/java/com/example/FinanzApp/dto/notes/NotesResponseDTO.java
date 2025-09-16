package com.example.FinanzApp.dto.notes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NotesResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdDate;
    private String comments;
}
