package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.transaction.TransactionRequestDTO;
import com.example.FinanzApp.dto.transaction.TransactionResponseDTO;

import java.util.List;

public interface ITransactionService {
    TransactionResponseDTO createTransaction(TransactionRequestDTO requestDTO);
    TransactionResponseDTO getTransactionById(Long id);
    List<TransactionResponseDTO> getAllTransactions();
    List<TransactionResponseDTO> getTransactionsByAccount(Long accountId);
    List<TransactionResponseDTO> getTransactionsByUserId(Long userId);
    TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO requestDTO);
    void deleteTransaction(Long id);
}
