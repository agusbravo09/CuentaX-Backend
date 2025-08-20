package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.transaction.TransactionRequestDTO;
import com.example.FinanzApp.dto.transaction.TransactionResponseDTO;
import com.example.FinanzApp.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toEntity(TransactionRequestDTO dto);

    @Mapping(source = "paymentMethod.name", target = "paymentMethodName")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "account.name", target = "accountName")
    TransactionResponseDTO toResponse(Transaction entity);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TransactionRequestDTO dto, @MappingTarget Transaction entity);
}
