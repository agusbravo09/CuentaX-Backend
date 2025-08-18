package com.example.FinanzApp.dto.loanPayment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoanPaymentResponseDTO {
    private Long id;
    private Double amount;
    private Date payDate;
    private Long loanId;
}
