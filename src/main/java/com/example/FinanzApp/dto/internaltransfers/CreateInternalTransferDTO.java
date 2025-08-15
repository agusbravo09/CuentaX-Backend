package com.example.FinanzApp.dto.internaltransfers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateInternalTransferDTO {
    private Long originAccountId;
    private Long destinyAccountId;
    private Double amount;
}
