package com.example.FinanzApp.dto.loans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoanPaymentDTO {
    private Long id;
    private Long loanId;
    private Double amountPaid;
    private Date date;
}
