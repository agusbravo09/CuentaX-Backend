package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t LEFT JOIN FETCH t.paymentMethod LEFT JOIN FETCH t.category LEFT JOIN FETCH t.account")
    List<Transaction> findAllWithRelations();
    @Query("SELECT t FROM Transaction t LEFT JOIN FETCH t.paymentMethod LEFT JOIN FETCH t.category LEFT JOIN FETCH t.account WHERE t.id = :id")
    Optional<Transaction> findByIdWithRelations(@Param("id") Long id);
    @Query("SELECT t FROM Transaction t LEFT JOIN FETCH t.paymentMethod LEFT JOIN FETCH t.category LEFT JOIN FETCH t.account WHERE t.account.id = :accountId")
    List<Transaction> findByAccountIdWithRelations(@Param("accountIid") Long accountId);
    @Query("SELECT t FROM Transaction t LEFT JOIN FETCH t.paymentMethod LEFT JOIN FETCH t.category LEFT JOIN FETCH t.account WHERE t.account.user.id = :userId")
    List<Transaction> findByUserId(@Param("userId") Long userId);
}
