package com.example.FinanzApp.dto.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ServicesResponseDTO {
    private Long id;
    private String name;
    private Double monthlyAmount;
    private Date expireDate;
    private String accountName;
    private String categoryName;
}
