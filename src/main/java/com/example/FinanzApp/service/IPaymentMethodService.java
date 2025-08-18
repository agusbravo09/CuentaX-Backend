package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.paymentMethod.PaymentMethodDTO;

import java.util.List;

public interface IPaymentMethodService {
    PaymentMethodDTO createPaymentMethod(PaymentMethodDTO dto);
    PaymentMethodDTO getPaymentMethodById(Long id);
    List<PaymentMethodDTO> getAllPaymentMethods();
    PaymentMethodDTO updatePaymentMethod(Long id, PaymentMethodDTO dto);
    void deletePaymentMethod(Long id);
}
