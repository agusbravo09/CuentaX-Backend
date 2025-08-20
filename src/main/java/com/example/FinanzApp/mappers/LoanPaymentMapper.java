package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.loanPayment.LoanPaymentRequestDTO;
import com.example.FinanzApp.dto.loanPayment.LoanPaymentResponseDTO;
import com.example.FinanzApp.model.LoanPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanPaymentMapper {
    LoanPaymentMapper INSTANCE = Mappers.getMapper(LoanPaymentMapper.class);

    LoanPayment toEntity(LoanPaymentRequestDTO dto);

    @Mapping(source = "loan.id", target = "loanId")
    LoanPaymentResponseDTO toResponse(LoanPayment entity);
}
