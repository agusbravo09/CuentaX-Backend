package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.transaction.TransactionRequestDTO;
import com.example.FinanzApp.dto.transaction.TransactionResponseDTO;
import com.example.FinanzApp.mappers.TransactionMapper;
import com.example.FinanzApp.model.Account;
import com.example.FinanzApp.model.Transaction;
import com.example.FinanzApp.model.enums.TransType;
import com.example.FinanzApp.repository.IAccountRepository;
import com.example.FinanzApp.repository.ICategoryRepository;
import com.example.FinanzApp.repository.IPaymentMethodRepository;
import com.example.FinanzApp.repository.ITransactionRepository;
import com.example.FinanzApp.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionsServiceImpl implements ITransactionService {
    @Autowired
    private ITransactionRepository transactionRepo;
    @Autowired
    private IAccountRepository accountRepo;
    @Autowired
    private IPaymentMethodRepository paymentMethodRepo;
    @Autowired
    private ICategoryRepository categoryRepo;
    @Autowired
    private TransactionMapper transactionMapper;


    @Override
    @Transactional
    public TransactionResponseDTO createTransaction(TransactionRequestDTO requestDTO) {
        Transaction transaction = transactionMapper.toEntity(requestDTO);

        //set related entities.
        transaction.setAccount(accountRepo.findById(requestDTO.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found")));
        transaction.setPaymentMethod(paymentMethodRepo.findById(requestDTO.getPaymentMethodId()).orElseThrow(() -> new RuntimeException("Payment method not fount")));
        transaction.setCategory(categoryRepo.findById(requestDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found")));

        //update account balance.
        updateAccountBalance(transaction);

        Transaction savedTransaction = transactionRepo.save(transaction);
        return transactionMapper.toResponse(savedTransaction);
    }

    @Override
    public TransactionResponseDTO getTransactionById(Long id) {
        Transaction transaction = transactionRepo.findByIdWithRelations(id).orElseThrow(() -> new RuntimeException("Transaction not found"));

        return transactionMapper.toResponse(transaction);
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionRepo.findAllWithRelations().stream().map(transactionMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsByAccount(Long accountId) {
        return transactionRepo.findByAccountIdWithRelations(accountId).stream().map(transactionMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO requestDTO) {
        Transaction existingTransaction = transactionRepo.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));

        //reverse old transaction effects.
        reverseTransactionEffect(existingTransaction);

        //update basic transaction details
        transactionMapper.updateEntityFromDto(requestDTO, existingTransaction);

        //set related entities
        existingTransaction.setAccount(accountRepo.findById(requestDTO.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found")));
        existingTransaction.setCategory(categoryRepo.findById(requestDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found")));
        existingTransaction.setPaymentMethod(paymentMethodRepo.findById(requestDTO.getPaymentMethodId()).orElseThrow(() -> new RuntimeException("Payment method not found")));

        //apply new transaction effects.
        updateAccountBalance(existingTransaction);

        //save
        Transaction updatedTransaction = transactionRepo.save(existingTransaction);

        //return
        return transactionMapper.toResponse(updatedTransaction);
    }

    @Override
    @Transactional
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepo.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        reverseTransactionEffect(transaction);
        transactionRepo.deleteById(id);
    }

    private void updateAccountBalance(Transaction transaction){
        Account account = transaction.getAccount();

        if(transaction.getType() == TransType.INCOME){
            account.setCurrentBalance(account.getCurrentBalance() + transaction.getAmount());
        } else{
          account.setCurrentBalance(account.getCurrentBalance() - transaction.getAmount());
        }

        accountRepo.save(account);
    }

    private void reverseTransactionEffect(Transaction transaction){
        Account account = transaction.getAccount();

        if(transaction.getType() == TransType.INCOME){
            account.setCurrentBalance(account.getCurrentBalance() - transaction.getAmount());
        } else{
            account.setCurrentBalance(account.getCurrentBalance() + transaction.getAmount());
        }

        accountRepo.save(account);
    }
}
