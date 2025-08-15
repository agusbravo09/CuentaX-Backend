package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.accounts.AccountDTO;
import com.example.FinanzApp.dto.accounts.CreateAccountDTO;
import com.example.FinanzApp.dto.accounts.UpdateAccountDTO;
import com.example.FinanzApp.model.Account;
import com.example.FinanzApp.model.User;
import com.example.FinanzApp.repository.IAccountRepository;
import com.example.FinanzApp.repository.IUserRepository;
import com.example.FinanzApp.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepo;
    @Autowired
    private IUserRepository userRepo;


    //Create a new Account
    @Override
    public AccountDTO create(CreateAccountDTO dto) {
        Account acc = new Account();

        acc.setName(dto.getName());
        acc.setType(dto.getAccountType());
        acc.setCurrentBalance(dto.getInitialBalance() != null ? dto.getInitialBalance() : 0.0);

        if(dto.getUserId() != null){
            User u = userRepo.findById(dto.getUserId())
                    .orElseThrow( () -> new NoSuchElementException("User not found: " + dto.getUserId()));
            acc.setUser(u);
        }

        accountRepo.save(acc);


        return toDTO(acc);
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public AccountDTO getById(Long id) {
        Account acc = accountRepo.findById(id).orElseThrow( () -> new NoSuchElementException("Account not found: " + id));
        return toDTO(acc);
    }

    @Override
    public AccountDTO update(Long id, UpdateAccountDTO dto) {
        Account acc = accountRepo.findById(id).orElseThrow( () -> new NoSuchElementException("Account not found: " + id));
        if(dto.getName() != null){
            acc.setName(dto.getName());
        }

        if(dto.getBalance() != null){
            acc.setCurrentBalance(dto.getBalance()); //!!normalmente no permitiría setear saldo directo!!
        }
        accountRepo.save(acc);

        return toDTO(acc);
    }

    @Override
    public void delete(Long id) {
        if(!accountRepo.existsById(id)){
            throw new NoSuchElementException("Account not found: " + id);
        }
        accountRepo.deleteById(id);

    }

    private AccountDTO toDTO(Account a){
        return new AccountDTO(
                a.getId(),
                a.getName(),
                a.getType() != null ? a.getType() : null,
                a.getCurrentBalance(),
                a.getUser() != null ? a.getUser().getId() : null
        );
    }
}
