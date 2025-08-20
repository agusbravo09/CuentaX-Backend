package com.example.FinanzApp.controller;

import com.example.FinanzApp.dto.paymentMethod.PaymentMethodDTO;
import com.example.FinanzApp.service.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment-methods")
public class PaymentMethodController {
    @Autowired
    private IPaymentMethodService paymentMethodService;

    @PostMapping
    public ResponseEntity<PaymentMethodDTO> createPaymentMethod(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        PaymentMethodDTO response = paymentMethodService.createPaymentMethod(paymentMethodDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethodById(@PathVariable Long id) {
        PaymentMethodDTO response = paymentMethodService.getPaymentMethodById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentMethodDTO>> getAllPaymentMethods() {
        List<PaymentMethodDTO> responses = paymentMethodService.getAllPaymentMethods();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> updatePaymentMethod(
            @PathVariable Long id,
            @RequestBody PaymentMethodDTO paymentMethodDTO) {
        PaymentMethodDTO response = paymentMethodService.updatePaymentMethod(id, paymentMethodDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Long id) {
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.noContent().build();
    }
}
