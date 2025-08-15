package com.example.FinanzApp.dto.loans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateLoanPaymentDTO {
    private Long loanId;
    private BigDecimal amountPaid;
}
