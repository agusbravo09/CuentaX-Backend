package com.example.FinanzApp.dto.loans;

import com.example.FinanzApp.model.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateLoanDTO {
    private Double amount;
    private LoanType loanType;
    private Long accountId;
}
