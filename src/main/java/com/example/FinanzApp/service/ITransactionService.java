package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.transactions.CreateTransactionDTO;
import com.example.FinanzApp.dto.transactions.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    List<TransactionDTO> getAll();
    List<TransactionDTO> getByAccountId(Long accountId);
    TransactionDTO getById(Long id);
    TransactionDTO create(CreateTransactionDTO dto);
    void delete(Long id);
}
