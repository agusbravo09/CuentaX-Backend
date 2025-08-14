package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.LoanPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanPaymentRepository extends JpaRepository<LoanPayment, Long> {
}
