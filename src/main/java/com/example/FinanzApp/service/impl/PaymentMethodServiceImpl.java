package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.paymentMethod.PaymentMethodDTO;
import com.example.FinanzApp.mappers.PaymentMethodMapper;
import com.example.FinanzApp.model.PaymentMethod;
import com.example.FinanzApp.repository.IPaymentMethodRepository;
import com.example.FinanzApp.service.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService {
    @Autowired
    private IPaymentMethodRepository paymentMethodRepo;
    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Override
    @Transactional
    public PaymentMethodDTO createPaymentMethod(PaymentMethodDTO dto) {
        PaymentMethod paymentMethod = paymentMethodMapper.toEntity(dto);
        PaymentMethod savedPaymentMethod = paymentMethodRepo.save(paymentMethod);
        return paymentMethodMapper.toResponse(savedPaymentMethod);
    }

    @Override
    public PaymentMethodDTO getPaymentMethodById(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepo.findById(id).orElseThrow(() -> new RuntimeException("Payment method not found"));
        return paymentMethodMapper.toResponse(paymentMethod);
    }

    @Override
    public List<PaymentMethodDTO> getAllPaymentMethods() {
        return paymentMethodRepo.findAll().stream().map(paymentMethodMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaymentMethodDTO updatePaymentMethod(Long id, PaymentMethodDTO dto) {
        PaymentMethod existingPaymentMethod = paymentMethodRepo.findById(id).orElseThrow(() -> new RuntimeException("Payment method not found"));

        existingPaymentMethod.setName(dto.getName());

        PaymentMethod updatedPaymentMethod = paymentMethodRepo.save(existingPaymentMethod);

        return paymentMethodMapper.toResponse(updatedPaymentMethod);
    }

    @Override
    @Transactional
    public void deletePaymentMethod(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepo.findById(id).orElseThrow(() -> new RuntimeException("Payment method not found"));

        if(!paymentMethod.getTransactions().isEmpty()){
            throw new RuntimeException("Cannot delete payment method with associated transactions");
        }

        paymentMethodRepo.delete(paymentMethod);
    }
}
