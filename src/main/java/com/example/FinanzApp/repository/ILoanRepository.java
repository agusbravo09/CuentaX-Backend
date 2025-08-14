package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long> {
}
