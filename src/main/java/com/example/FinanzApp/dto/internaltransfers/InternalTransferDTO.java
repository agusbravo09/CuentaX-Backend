package com.example.FinanzApp.dto.internaltransfers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InternalTransferDTO {
    private Long id;
    private Long originAccountId;
    private Long destinationAccountId;
    private Double amount;
    private String date;
}
