package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.internalTransfer.InternalTransferRequestDTO;
import com.example.FinanzApp.dto.internalTransfer.InternalTransferResponseDTO;

import java.util.List;

public interface IInternalTransferService {
    InternalTransferResponseDTO createInternalTransfer(InternalTransferRequestDTO requestDTO);
    InternalTransferResponseDTO getInternalTransferById(Long id);
    List<InternalTransferResponseDTO> getAllInternalTransfers();
    List<InternalTransferResponseDTO> getTransfersByAccount(Long accountId);
    void deleteInternalTransfer(Long id);
}
