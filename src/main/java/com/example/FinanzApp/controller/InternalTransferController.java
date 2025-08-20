package com.example.FinanzApp.controller;

import com.example.FinanzApp.dto.internalTransfer.InternalTransferRequestDTO;
import com.example.FinanzApp.dto.internalTransfer.InternalTransferResponseDTO;
import com.example.FinanzApp.service.IInternalTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/internal-transfers")
public class InternalTransferController {
    @Autowired
    private IInternalTransferService internalTransferService;

    @PostMapping
    public ResponseEntity<InternalTransferResponseDTO> createInternalTransfer(@RequestBody InternalTransferRequestDTO requestDTO) {
        InternalTransferResponseDTO response = internalTransferService.createInternalTransfer(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternalTransferResponseDTO> getInternalTransferById(@PathVariable Long id) {
        InternalTransferResponseDTO response = internalTransferService.getInternalTransferById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InternalTransferResponseDTO>> getAllInternalTransfers() {
        List<InternalTransferResponseDTO> responses = internalTransferService.getAllInternalTransfers();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<InternalTransferResponseDTO>> getTransfersByAccount(@PathVariable Long accountId) {
        List<InternalTransferResponseDTO> responses = internalTransferService.getTransfersByAccount(accountId);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternalTransfer(@PathVariable Long id) {
        internalTransferService.deleteInternalTransfer(id);
        return ResponseEntity.noContent().build();
    }
}
