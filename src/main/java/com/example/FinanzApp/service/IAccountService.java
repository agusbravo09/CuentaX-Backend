package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.accounts.*;

import java.util.List;

public interface IAccountService {
    AccountDTO create(CreateAccountDTO dto);
    List<AccountDTO> getAll();
    AccountDTO getById(Long id);
    AccountDTO update(Long id, UpdateAccountDTO dto);
    void delete(Long id);
}
