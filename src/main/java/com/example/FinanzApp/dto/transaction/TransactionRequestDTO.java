package com.example.FinanzApp.dto.transaction;

import com.example.FinanzApp.model.enums.TransType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionRequestDTO {
    private TransType type;
    private Double amount;
    private String description;
    private LocalDate date;
    private Long paymentMethodId;
    private Long categoryId;
    private Long accountId;
}
