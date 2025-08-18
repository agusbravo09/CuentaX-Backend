package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.InternalTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInternalTransferRepository extends JpaRepository<InternalTransfer, Long> {
    List<InternalTransfer> findByOriginAccountId(Long accountId);
    List<InternalTransfer> findByDestinationAccountId(Long accountId);
}
