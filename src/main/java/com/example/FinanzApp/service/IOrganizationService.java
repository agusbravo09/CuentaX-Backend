package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.organization.OrganizationRequestDTO;
import com.example.FinanzApp.dto.organization.OrganizationResponseDTO;

import java.util.List;

public interface IOrganizationService {
    OrganizationResponseDTO createOrganization(OrganizationRequestDTO dto);
    List<OrganizationResponseDTO> getOrganizationsByUserId(Long userId);
    List<OrganizationResponseDTO> getAllOrganizations();
    OrganizationResponseDTO getOrganizationById(Long organizationId);
    OrganizationResponseDTO updateOrganization(Long organizationId, OrganizationRequestDTO dto);
    void deleteOrganization(Long organizationId);
}
