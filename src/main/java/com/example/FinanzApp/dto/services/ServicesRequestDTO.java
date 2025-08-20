package com.example.FinanzApp.dto.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ServicesRequestDTO {
    private String name;
    private Double monthlyAmount;
    private LocalDate expireDate;
    private Long accountId;
    private Long categoryId;
}
