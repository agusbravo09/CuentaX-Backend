package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.services.ServicesRequestDTO;
import com.example.FinanzApp.dto.services.ServicesResponseDTO;
import com.example.FinanzApp.model.Services;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ServicesMapper {
    ServicesMapper INSTANCE = Mappers.getMapper(ServicesMapper.class);

    Services toEntity(ServicesRequestDTO dto);

    @Mapping(source = "account.name", target = "accountName")
    @Mapping(source = "category.name", target = "categoryName")
    ServicesResponseDTO toResponse(Services entity);
}
