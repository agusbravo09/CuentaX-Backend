package com.example.FinanzApp.dto.notes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NotesRequestDTO {
    private String title;
    private String content;
    private Long userId;
    private String comments;
}
