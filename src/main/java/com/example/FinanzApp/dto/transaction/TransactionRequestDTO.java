package com.example.FinanzApp.dto.transaction;

import com.example.FinanzApp.model.enums.TransType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionRequestDTO {
    private TransType type;
    private Double amount;
    private String description;
    private Date date;
    private Long paymentMethodId;
    private Long categoryId;
    private Long accountId;
}
