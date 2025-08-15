package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.payments.CreatePaymentMethodDTO;
import com.example.FinanzApp.dto.payments.PaymentMethodDTO;

import java.util.List;

public interface IPaymentMethodService {
    List<PaymentMethodDTO> getAll();
    PaymentMethodDTO getById(Long id);
    PaymentMethodDTO create(CreatePaymentMethodDTO dto);
    PaymentMethodDTO update(Long id, CreatePaymentMethodDTO dto);
    void delete(Long id);
}
