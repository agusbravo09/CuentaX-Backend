package com.example.FinanzApp.dto.internalTransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InternalTransferResponseDTO {
    private Long id;
    private Double amount;
    private Date date;
    private String originAccountName;
    private String destinationAccountName;
}
