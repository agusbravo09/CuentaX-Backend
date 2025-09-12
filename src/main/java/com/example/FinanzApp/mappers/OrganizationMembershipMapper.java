package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.organizationMembership.OrganizationMembershipRequestDTO;
import com.example.FinanzApp.dto.organizationMembership.OrganizationMembershipResponseDTO;
import com.example.FinanzApp.model.OrganizationMembership;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationMembershipMapper {
    OrganizationMembershipMapper INSTANCE = Mappers.getMapper(OrganizationMembershipMapper.class);

    OrganizationMembership toEntity(OrganizationMembershipRequestDTO requestDTO);

    @Mapping(source = "organization.name", target = "organizationName")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "user.email", target = "userEmail")
    OrganizationMembershipResponseDTO toResponse(OrganizationMembership entity);
}
