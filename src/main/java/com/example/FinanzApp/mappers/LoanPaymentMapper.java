package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.loanPayment.LoanPaymentRequestDTO;
import com.example.FinanzApp.dto.loanPayment.LoanPaymentResponseDTO;
import com.example.FinanzApp.model.LoanPayment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanPaymentMapper {
    LoanPaymentMapper INSTANCE = Mappers.getMapper(LoanPaymentMapper.class);

    LoanPayment toEntity(LoanPaymentRequestDTO dto);
    LoanPaymentResponseDTO toResponse(LoanPayment entity);
}
