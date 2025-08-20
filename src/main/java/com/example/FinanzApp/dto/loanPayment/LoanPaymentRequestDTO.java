package com.example.FinanzApp.dto.loanPayment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoanPaymentRequestDTO {
    private Double amount;
    private LocalDate payDate;
    private Long loanId;
}
