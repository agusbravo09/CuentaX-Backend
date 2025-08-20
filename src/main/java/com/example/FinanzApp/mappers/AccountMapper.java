package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.account.AccountRequestDTO;
import com.example.FinanzApp.dto.account.AccountResponseDTO;
import com.example.FinanzApp.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toEntity(AccountRequestDTO dto);
    @Mapping(source = "user.name", target = "userName")
    AccountResponseDTO toResponse(Account entity);
}
