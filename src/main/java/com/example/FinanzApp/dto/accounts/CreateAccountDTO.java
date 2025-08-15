package com.example.FinanzApp.dto.accounts;

import com.example.FinanzApp.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateAccountDTO {
    private String name;
    private AccountType accountType;
    private Double initialBalance;
    private Long userId;
}
