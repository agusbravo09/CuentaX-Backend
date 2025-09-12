package com.example.FinanzApp.dto.account;

import com.example.FinanzApp.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AccountRequestDTO {
    private String name;
    private AccountType type;
    private Double currentBalance;
    private Long userId;
    private Long organizationId;
}
