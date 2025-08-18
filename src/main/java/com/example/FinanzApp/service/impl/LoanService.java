package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.loan.LoanRequestDTO;
import com.example.FinanzApp.dto.loan.LoanResponseDTO;
import com.example.FinanzApp.mappers.LoanMapper;
import com.example.FinanzApp.model.Loan;
import com.example.FinanzApp.model.User;
import com.example.FinanzApp.model.enums.LoanState;
import com.example.FinanzApp.repository.ILoanRepository;
import com.example.FinanzApp.repository.IUserRepository;
import com.example.FinanzApp.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService implements ILoanService {
    @Autowired
    private ILoanRepository loanRepo;
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private LoanMapper loanMapper;

    @Override
    @Transactional
    public LoanResponseDTO createLoan(LoanRequestDTO requestDTO) {
        User user = userRepo.findById(requestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Loan loan = loanMapper.toEntity(requestDTO);
        loan.setUser(user);
        loan.setState(LoanState.PENDING);

        Loan savedLoan = loanRepo.save(loan);
        return loanMapper.toResponse(savedLoan);
    }

    @Override
    public LoanResponseDTO getLoanById(Long id) {
        Loan loan = loanRepo.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));

        return loanMapper.toResponse(loan);
    }

    @Override
    public List<LoanResponseDTO> getAllLoans() {
        return loanRepo.findAll().stream().map(loanMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<LoanResponseDTO> getLoansByUser(Long userId) {
        return loanRepo.findByUserId(userId).stream().map(loanMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LoanResponseDTO updateLoan(Long id, LoanRequestDTO requestDTO) {
        Loan existingLoan = loanRepo.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));

        existingLoan.setType(requestDTO.getType());
        existingLoan.setTotalAmount(requestDTO.getTotalAmount());
        existingLoan.setInvolvedPerson(requestDTO.getInvolvedPerson());
        existingLoan.setStartDate(requestDTO.getStartDate());
        existingLoan.setEndDate(requestDTO.getEndDate());

        Loan updatedLoan = loanRepo.save(existingLoan);
        return loanMapper.toResponse(updatedLoan);
    }

    @Override
    @Transactional
    public void deleteLoan(Long id) {
        Loan loan = loanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        if (!loan.getLoanPayments().isEmpty()) {
            throw new RuntimeException("Cannot delete loan with associated payments");
        }

        loanRepo.delete(loan);

    }

    @Override
    @Transactional
    public LoanResponseDTO updateLoanState(Long id, LoanState state) {
        Loan loan = loanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        try {
            loan.setState(state);
            Loan updatedLoan = loanRepo.save(loan);
            return loanMapper.toResponse(updatedLoan);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid loan state: " + state);
        }
    }
}
