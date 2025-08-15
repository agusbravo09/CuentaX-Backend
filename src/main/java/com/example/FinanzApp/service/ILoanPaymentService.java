package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.loans.CreateLoanPaymentDTO;
import com.example.FinanzApp.dto.loans.LoanPaymentDTO;

import java.util.List;

public interface ILoanPaymentService {
    List<LoanPaymentDTO> getAll();
    List<LoanPaymentDTO> getByLoanId(Long loanId);
    LoanPaymentDTO getById(Long id);
    LoanPaymentDTO create(CreateLoanPaymentDTO dto);
    void delete(Long id);
}
