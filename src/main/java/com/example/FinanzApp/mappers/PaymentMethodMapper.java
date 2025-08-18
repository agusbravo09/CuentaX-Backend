package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.paymentMethod.PaymentMethodDTO;
import com.example.FinanzApp.model.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    PaymentMethod toEntity(PaymentMethodDTO dto);
    PaymentMethodDTO toResponse(PaymentMethod entity);
}
