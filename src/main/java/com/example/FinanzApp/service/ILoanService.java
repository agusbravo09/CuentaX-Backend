package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.loan.LoanRequestDTO;
import com.example.FinanzApp.dto.loan.LoanResponseDTO;
import com.example.FinanzApp.model.enums.LoanState;

import java.util.List;

public interface ILoanService {
    LoanResponseDTO createLoan(LoanRequestDTO requestDTO);
    LoanResponseDTO getLoanById(Long id);
    List<LoanResponseDTO> getAllLoans();
    List<LoanResponseDTO> getLoansByUser(Long userId);
    LoanResponseDTO updateLoan(Long id, LoanRequestDTO requestDTO);
    void deleteLoan(Long id);
    LoanResponseDTO updateLoanState(Long id, LoanState state);
}
