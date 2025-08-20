package com.example.FinanzApp.controller;

import com.example.FinanzApp.dto.transaction.TransactionRequestDTO;
import com.example.FinanzApp.dto.transaction.TransactionResponseDTO;
import com.example.FinanzApp.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO requestDTO){
        TransactionResponseDTO response = transactionService.createTransaction(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long id){
        TransactionResponseDTO response = transactionService.getTransactionById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions(){
        List<TransactionResponseDTO> responses = transactionService.getAllTransactions();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByAccount(@PathVariable Long accountId){
        List<TransactionResponseDTO> responses = transactionService.getTransactionsByAccount(accountId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable Long id,
                                                                    @RequestBody TransactionRequestDTO requestDTO){
        TransactionResponseDTO response = transactionService.updateTransaction(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }





}
