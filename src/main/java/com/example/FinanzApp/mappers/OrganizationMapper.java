package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.organization.OrganizationRequestDTO;
import com.example.FinanzApp.dto.organization.OrganizationResponseDTO;
import com.example.FinanzApp.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
   OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    Organization toEntity(OrganizationRequestDTO requestDTO);

    @Mapping(source = "owner.name", target = "ownerName")
    OrganizationResponseDTO toResponse(Organization entity);
}
