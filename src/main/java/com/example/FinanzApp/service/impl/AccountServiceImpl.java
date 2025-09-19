package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.account.AccountRequestDTO;
import com.example.FinanzApp.dto.account.AccountResponseDTO;
import com.example.FinanzApp.mappers.AccountMapper;
import com.example.FinanzApp.model.Account;
import com.example.FinanzApp.repository.IAccountRepository;
import com.example.FinanzApp.repository.IUserRepository;
import com.example.FinanzApp.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository accountRepo;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private AccountMapper accountMapper;


    @Override
    @Transactional
    public AccountResponseDTO createAccount(AccountRequestDTO requestDTO) {
        Account a = accountMapper.toEntity(requestDTO);

        if(requestDTO.getUserId() != null){
            a.setUser(userRepo.findById(requestDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found")));
        }

        a.setCreatedAt(LocalDate.now());

        Account savedAccount = accountRepo.save(a);

        return accountMapper.toResponse(savedAccount);
    }

    @Override
    public AccountResponseDTO getAccountById(Long id) {
        Account a = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));

        return accountMapper.toResponse(a);
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepo.findAll().stream().map(accountMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<AccountResponseDTO> getAccountsByUserId(Long userId) {
        return accountRepo.findByUserId(userId).stream().map(accountMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AccountResponseDTO updateAccount(Long id, AccountRequestDTO requestDTO) {
        Account existingAccount = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));

        existingAccount.setName(requestDTO.getName());
        existingAccount.setType(requestDTO.getType());
        existingAccount.setCurrentBalance(requestDTO.getCurrentBalance());

        Account updatedAccount = accountRepo.save(existingAccount);

        return accountMapper.toResponse(updatedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepo.deleteById(id);
    }

    @Override
    public Double getAccountBalance(Long accountId) {
        Account a = accountRepo.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));

        return a.getCurrentBalance();
    }
}
