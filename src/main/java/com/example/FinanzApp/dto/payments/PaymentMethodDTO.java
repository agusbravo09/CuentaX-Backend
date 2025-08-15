package com.example.FinanzApp.dto.payments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PaymentMethodDTO {
    private Long id;
    private String name;
}
