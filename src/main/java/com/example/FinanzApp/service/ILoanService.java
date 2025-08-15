package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.loans.CreateLoanDTO;
import com.example.FinanzApp.dto.loans.LoanDTO;

import java.util.List;

public interface ILoanService {
    List<LoanDTO> getAll();
    LoanDTO getById(Long id);
    LoanDTO create(CreateLoanDTO dto);
    LoanDTO close(Long id);
    void delete(Long id);
}
