package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.internalTransfer.InternalTransferRequestDTO;
import com.example.FinanzApp.dto.internalTransfer.InternalTransferResponseDTO;
import com.example.FinanzApp.mappers.InternalTransferMapper;
import com.example.FinanzApp.model.Account;
import com.example.FinanzApp.model.InternalTransfer;
import com.example.FinanzApp.repository.IAccountRepository;
import com.example.FinanzApp.repository.IInternalTransferRepository;
import com.example.FinanzApp.service.IInternalTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InternalTransferImpl implements IInternalTransferService {
    @Autowired
    private IInternalTransferRepository internalTransferRepo;
    @Autowired
    private InternalTransferMapper internalTransferMapper;
    @Autowired
    private IAccountRepository accountRepo;

    @Override
    @Transactional
    public InternalTransferResponseDTO createInternalTransfer(InternalTransferRequestDTO requestDTO) {
        //validate accounts
        Account originAccount = accountRepo.findById(requestDTO.getOriginAccountId()).orElseThrow(() -> new RuntimeException("Origin account not found"));
        Account destinationAccount = accountRepo.findById(requestDTO.getDestinationAccountId()).orElseThrow(() -> new RuntimeException("Destination account not found"));

        //validate same account transfer
        if(originAccount.getId().equals(destinationAccount.getId())){
            throw new RuntimeException("Cannot transfer to the same account");
        }

        //validate sufficient balance
        if(originAccount.getCurrentBalance() < requestDTO.getAmount()){
            throw new RuntimeException("Insufficient fund in origin account");
        }

        //create transfer
        InternalTransfer transfer = new InternalTransfer();
        transfer.setAmount(requestDTO.getAmount());
        transfer.setDate(LocalDate.now());
        transfer.setOriginAccount(originAccount);
        transfer.setDestinationAccount(destinationAccount);

        //update account balances
        originAccount.setCurrentBalance(originAccount.getCurrentBalance() - requestDTO.getAmount());
        destinationAccount.setCurrentBalance(destinationAccount.getCurrentBalance() + requestDTO.getAmount());

        //save changes
        accountRepo.save(originAccount);
        accountRepo.save(destinationAccount);
        InternalTransfer savedTransfer = internalTransferRepo.save(transfer);

        return internalTransferMapper.toResponse(savedTransfer);
    }

    @Override
    public InternalTransferResponseDTO getInternalTransferById(Long id) {
        InternalTransfer internalTransfer = internalTransferRepo.findById(id).orElseThrow(() -> new RuntimeException("Internal transfer not found"));
        return internalTransferMapper.toResponse(internalTransfer);
    }

    @Override
    public List<InternalTransferResponseDTO> getAllInternalTransfers() {
        return internalTransferRepo.findAll().stream().map(internalTransferMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<InternalTransferResponseDTO> getTransfersByAccount(Long accountId) {
        List<InternalTransfer> outgoing = internalTransferRepo.findByOriginAccountId(accountId);
        List<InternalTransfer> incoming = internalTransferRepo.findByDestinationAccountId(accountId);

        return Stream.concat(outgoing.stream(), incoming.stream()).map(internalTransferMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteInternalTransfer(Long id) {
        InternalTransfer transfer = internalTransferRepo.findById(id).orElseThrow(() -> new RuntimeException("Internal Transfer not found"));

        //reverse the transfer.
        Account originAccount = transfer.getOriginAccount();
        Account destionationAccount = transfer.getDestinationAccount();

        originAccount.setCurrentBalance(originAccount.getCurrentBalance() + transfer.getAmount());
        destionationAccount.setCurrentBalance(destionationAccount.getCurrentBalance() - transfer.getAmount());

        //save account changes
        accountRepo.save(originAccount);
        accountRepo.save(destionationAccount);

        //delete the transfer.
        internalTransferRepo.delete(transfer);
    }
}
