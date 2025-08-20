package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.loan.LoanRequestDTO;
import com.example.FinanzApp.dto.loan.LoanResponseDTO;
import com.example.FinanzApp.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    Loan toEntity(LoanRequestDTO dto);

    @Mapping(source = "user.name", target = "userName")
    LoanResponseDTO toResponse(Loan entity);
}
