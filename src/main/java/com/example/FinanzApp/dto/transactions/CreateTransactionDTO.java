package com.example.FinanzApp.dto.transactions;

import com.example.FinanzApp.model.enums.TransType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateTransactionDTO {
    private String description;
    private TransType type;
    private Double amount;
    private Long categoryId;
    private Long accountId;
}
