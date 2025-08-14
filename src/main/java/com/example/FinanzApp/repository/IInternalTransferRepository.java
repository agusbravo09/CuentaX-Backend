package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.InternalTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInternalTransferRepository extends JpaRepository<InternalTransfer, Long> {
}
