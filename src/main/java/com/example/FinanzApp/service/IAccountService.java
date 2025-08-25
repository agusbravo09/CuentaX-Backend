package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.account.AccountRequestDTO;
import com.example.FinanzApp.dto.account.AccountResponseDTO;

import java.util.List;

public interface IAccountService {
    AccountResponseDTO createAccount(AccountRequestDTO requestDTO);
    AccountResponseDTO getAccountById(Long id);
    List<AccountResponseDTO> getAllAccounts();
    List<AccountResponseDTO> getAccountsByUserId(Long userId);
    AccountResponseDTO updateAccount(Long id, AccountRequestDTO requestDTO);
    void deleteAccount(Long id);
    Double getAccountBalance(Long accountId);
}
