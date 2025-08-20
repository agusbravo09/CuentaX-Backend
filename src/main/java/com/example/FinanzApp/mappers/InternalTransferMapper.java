package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.internalTransfer.InternalTransferRequestDTO;
import com.example.FinanzApp.dto.internalTransfer.InternalTransferResponseDTO;
import com.example.FinanzApp.model.InternalTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InternalTransferMapper {
    InternalTransferMapper INSTANCE = Mappers.getMapper(InternalTransferMapper.class);

    InternalTransfer toEntity(InternalTransferRequestDTO dto);

    @Mapping(source = "originAccount.name", target = "originAccountName")
    @Mapping(source = "destinationAccount.name", target = "destinationAccountName")
    InternalTransferResponseDTO toResponse(InternalTransfer entity);
}
