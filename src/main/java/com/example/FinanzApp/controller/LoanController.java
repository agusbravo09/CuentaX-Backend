package com.example.FinanzApp.controller;

import com.example.FinanzApp.dto.loan.LoanRequestDTO;
import com.example.FinanzApp.dto.loan.LoanResponseDTO;
import com.example.FinanzApp.model.enums.LoanState;
import com.example.FinanzApp.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    @Autowired
    private ILoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponseDTO> createLoan(@RequestBody LoanRequestDTO requestDTO) {
        LoanResponseDTO response = loanService.createLoan(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long id) {
        LoanResponseDTO response = loanService.getLoanById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanResponseDTO>> getAllLoans() {
        List<LoanResponseDTO> responses = loanService.getAllLoans();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanResponseDTO>> getLoansByUser(@PathVariable Long userId) {
        List<LoanResponseDTO> responses = loanService.getLoansByUser(userId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> updateLoan(
            @PathVariable Long id,
            @RequestBody LoanRequestDTO requestDTO) {
        LoanResponseDTO response = loanService.updateLoan(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/state/{state}")
    public ResponseEntity<LoanResponseDTO> updateLoanState(
            @PathVariable Long id,
            @PathVariable LoanState state) {
        LoanResponseDTO response = loanService.updateLoanState(id, state);
        return ResponseEntity.ok(response);
    }
}
