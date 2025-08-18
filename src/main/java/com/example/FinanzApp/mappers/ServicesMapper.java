package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.services.ServicesRequestDTO;
import com.example.FinanzApp.dto.services.ServicesResponseDTO;
import com.example.FinanzApp.model.Services;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ServicesMapper {
    ServicesMapper INSTANCE = Mappers.getMapper(ServicesMapper.class);

    Services toEntity(ServicesRequestDTO dto);
    ServicesResponseDTO toResponse(Services entity);
}
