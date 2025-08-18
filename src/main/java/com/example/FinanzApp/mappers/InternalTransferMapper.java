package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.internalTransfer.InternalTransferRequestDTO;
import com.example.FinanzApp.dto.internalTransfer.InternalTransferResponseDTO;
import com.example.FinanzApp.model.InternalTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InternalTransferMapper {
    InternalTransferMapper INSTANCE = Mappers.getMapper(InternalTransferMapper.class);

    InternalTransfer toEntity(InternalTransferRequestDTO dto);
    InternalTransferResponseDTO toResponse(InternalTransfer entity);
}
