package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long userId);
}
