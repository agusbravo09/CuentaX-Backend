package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.internaltransfers.CreateInternalTransferDTO;
import com.example.FinanzApp.dto.internaltransfers.InternalTransferDTO;

import java.util.List;

public interface IInternalTransferService {
    List<InternalTransferDTO> getAll();
    InternalTransferDTO getById(Long id);
    InternalTransferDTO create(CreateInternalTransferDTO dto);
    void delete(Long id);
}
