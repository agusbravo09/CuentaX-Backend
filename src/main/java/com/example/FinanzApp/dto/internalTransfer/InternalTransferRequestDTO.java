package com.example.FinanzApp.dto.internalTransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InternalTransferRequestDTO {
    private Double amount;
    private Long originAccountId;
    private Long destinationAccountId;

}
