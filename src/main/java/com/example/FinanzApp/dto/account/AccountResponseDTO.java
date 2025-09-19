package com.example.FinanzApp.dto.account;

import com.example.FinanzApp.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AccountResponseDTO {
    private Long id;
    private String name;
    private AccountType type;
    private Double currentBalance;
    private String userName;
    private Date createdAt;
    private String organizationName;
}
