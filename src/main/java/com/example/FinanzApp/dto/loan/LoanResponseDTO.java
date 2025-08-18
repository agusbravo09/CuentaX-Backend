package com.example.FinanzApp.dto.loan;

import com.example.FinanzApp.model.enums.LoanState;
import com.example.FinanzApp.model.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoanResponseDTO {
    private Long id;
    private LoanType type;
    private Double totalAmount;
    private String involvedPerson;
    private Date startDate;
    private Date endDate;
    private LoanState state;
    private String userName;
}
