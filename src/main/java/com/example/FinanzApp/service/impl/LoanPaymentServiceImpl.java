package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.loanPayment.LoanPaymentRequestDTO;
import com.example.FinanzApp.dto.loanPayment.LoanPaymentResponseDTO;
import com.example.FinanzApp.mappers.LoanPaymentMapper;
import com.example.FinanzApp.model.Loan;
import com.example.FinanzApp.model.LoanPayment;
import com.example.FinanzApp.model.enums.LoanState;
import com.example.FinanzApp.repository.ILoanPaymentRepository;
import com.example.FinanzApp.repository.ILoanRepository;
import com.example.FinanzApp.service.ILoanPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanPaymentServiceImpl implements ILoanPaymentService {
    @Autowired
    private ILoanRepository loanRepo;
    @Autowired
    private ILoanPaymentRepository loanPaymentRepo;
    @Autowired
    private LoanPaymentMapper loanPaymentMapper;

    @Override
    @Transactional
    public LoanPaymentResponseDTO createLoanPayment(LoanPaymentRequestDTO requestDTO) {
        Loan loan = loanRepo.findById(requestDTO.getLoanId()).orElseThrow(() -> new RuntimeException("Loan not found"));

        LoanPayment payment = loanPaymentMapper.toEntity(requestDTO);
        payment.setLoan(loan);

        LoanPayment savedPayment = loanPaymentRepo.save(payment);
        updateLoanState(loan);

        return loanPaymentMapper.toResponse(savedPayment);
    }

    @Override
    public LoanPaymentResponseDTO getLoanPaymentById(Long id) {
        LoanPayment payment = loanPaymentRepo.findById(id).orElseThrow(() -> new RuntimeException("Loan payment not found"));
        return loanPaymentMapper.toResponse(payment);
    }

    @Override
    public List<LoanPaymentResponseDTO> getAllLoanPayments() {
        return loanPaymentRepo.findAll().stream().map(loanPaymentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<LoanPaymentResponseDTO> getPaymentsByLoanId(Long loanId) {
        return loanPaymentRepo.findByLoanId(loanId).stream().map(loanPaymentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LoanPaymentResponseDTO updateLoanPayment(Long id, LoanPaymentRequestDTO requestDTO) {
        LoanPayment existingPayment = loanPaymentRepo.findById(id).orElseThrow(() -> new RuntimeException("Loan Payment not found"));

        Loan loan = loanRepo.findById(requestDTO.getLoanId()).orElseThrow(() -> new RuntimeException("Loan not found"));

        existingPayment.setAmount(requestDTO.getAmount());
        existingPayment.setPayDate(requestDTO.getPayDate());
        existingPayment.setLoan(loan);

        LoanPayment updatedPayment = loanPaymentRepo.save(existingPayment);
        updateLoanState(loan);

        return loanPaymentMapper.toResponse(updatedPayment);
    }

    @Override
    @Transactional
    public void deleteLoanPayment(Long id) {
        LoanPayment payment = loanPaymentRepo.findById(id).orElseThrow(() -> new RuntimeException("Loan payment not found"));
        Loan loan = payment.getLoan();
        loanPaymentRepo.delete(payment);
        updateLoanState(loan);
    }

    private void updateLoanState(Loan loan){
        double totalPaid = loan.getLoanPayments().stream().mapToDouble(LoanPayment::getAmount).sum();

        if(totalPaid >= loan.getTotalAmount()){
            loan.setState(LoanState.PAID);
        } else if(totalPaid > 0){
            loan.setState(LoanState.PARTIAL);
        } else{
          loan.setState(LoanState.PENDING);
        }
        loanRepo.save(loan);
    }
}
