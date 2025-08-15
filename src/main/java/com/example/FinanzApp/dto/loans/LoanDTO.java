package com.example.FinanzApp.dto.loans;

import com.example.FinanzApp.model.enums.LoanState;
import com.example.FinanzApp.model.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoanDTO {
    private Long id;
    private Double amount;
    private LoanType loanType;
    private LoanState loanState;
    private Double remainingBalance;
    private Long accountId;
}
