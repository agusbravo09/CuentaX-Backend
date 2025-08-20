package com.example.FinanzApp.controller;

import com.example.FinanzApp.dto.loanPayment.LoanPaymentRequestDTO;
import com.example.FinanzApp.dto.loanPayment.LoanPaymentResponseDTO;
import com.example.FinanzApp.service.ILoanPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan-payments")
public class LoanPaymentController {
    @Autowired
    private ILoanPaymentService loanPaymentService;

    @PostMapping
    public ResponseEntity<LoanPaymentResponseDTO> createLoanPayment(@RequestBody LoanPaymentRequestDTO requestDTO) {
        LoanPaymentResponseDTO response = loanPaymentService.createLoanPayment(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanPaymentResponseDTO> getLoanPaymentById(@PathVariable Long id) {
        LoanPaymentResponseDTO response = loanPaymentService.getLoanPaymentById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanPaymentResponseDTO>> getAllLoanPayments() {
        List<LoanPaymentResponseDTO> responses = loanPaymentService.getAllLoanPayments();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<LoanPaymentResponseDTO>> getPaymentsByLoanId(@PathVariable Long loanId) {
        List<LoanPaymentResponseDTO> responses = loanPaymentService.getPaymentsByLoanId(loanId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanPaymentResponseDTO> updateLoanPayment(
            @PathVariable Long id,
            @RequestBody LoanPaymentRequestDTO requestDTO) {
        LoanPaymentResponseDTO response = loanPaymentService.updateLoanPayment(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanPayment(@PathVariable Long id) {
        loanPaymentService.deleteLoanPayment(id);
        return ResponseEntity.noContent().build();
    }
}
