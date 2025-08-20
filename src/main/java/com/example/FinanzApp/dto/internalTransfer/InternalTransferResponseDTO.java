package com.example.FinanzApp.dto.internalTransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InternalTransferResponseDTO {
    private Long id;
    private Double amount;
    private LocalDate date;
    private String originAccountName;
    private String destinationAccountName;
}
