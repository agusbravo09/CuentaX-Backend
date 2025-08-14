package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
