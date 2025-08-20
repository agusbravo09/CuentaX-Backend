package com.example.FinanzApp.dto.loan;

import com.example.FinanzApp.model.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoanRequestDTO {
    private LoanType type;
    private Double totalAmount;
    private String involvedPerson;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
}
