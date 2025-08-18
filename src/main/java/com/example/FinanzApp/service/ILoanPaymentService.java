package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.loanPayment.LoanPaymentRequestDTO;
import com.example.FinanzApp.dto.loanPayment.LoanPaymentResponseDTO;

import java.util.List;

public interface ILoanPaymentService {
    LoanPaymentResponseDTO createLoanPayment(LoanPaymentRequestDTO requestDTO);
    LoanPaymentResponseDTO getLoanPaymentById(Long id);
    List<LoanPaymentResponseDTO> getAllLoanPayments();
    List<LoanPaymentResponseDTO> getPaymentsByLoanId(Long loanId);
    LoanPaymentResponseDTO updateLoanPayment(Long id, LoanPaymentRequestDTO requestDTO);
    void deleteLoanPayment(Long id);
}
