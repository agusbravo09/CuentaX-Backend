package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    @EntityGraph(attributePaths = {"paymentMethod", "category", "account"})
    List<Transaction> findByUserId(Long userId);
    @EntityGraph(attributePaths = {"paymentMethod", "category", "account"})
    List<Transaction> findByAccountId(Long accountId);
}
