package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.organization.OrganizationRequestDTO;
import com.example.FinanzApp.dto.organization.OrganizationResponseDTO;
import com.example.FinanzApp.mappers.OrganizationMapper;
import com.example.FinanzApp.model.Organization;
import com.example.FinanzApp.model.User;
import com.example.FinanzApp.repository.IOrganizationRepository;
import com.example.FinanzApp.repository.IUserRepository;
import com.example.FinanzApp.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements IOrganizationService {
    @Autowired
    private IOrganizationRepository orgRepo;
    @Autowired
    private OrganizationMapper mapper;
    @Autowired
    private IUserRepository userRepo;

    @Override
    @Transactional
    public OrganizationResponseDTO createOrganization(OrganizationRequestDTO dto) {
        User owner = userRepo.findById(dto.getOwnerId()).orElseThrow(() -> new RuntimeException("Owner not found"));
        Organization org = mapper.toEntity(dto);
        org.setOwner(owner);

        Organization savedOrg = orgRepo.save(org);

        return mapper.toResponse(savedOrg);
    }

    @Override
    public List<OrganizationResponseDTO> getOrganizationsByUserId(Long userId) {
        return orgRepo.findByOwnerId(userId).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<OrganizationResponseDTO> getAllOrganizations() {
        return orgRepo.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public OrganizationResponseDTO getOrganizationById(Long organizationId) {
        return orgRepo.findById(organizationId).map(mapper::toResponse).orElseThrow(() -> new RuntimeException("Organization not found"));
    }

    @Override
    @Transactional
    public OrganizationResponseDTO updateOrganization(Long organizationId, OrganizationRequestDTO dto) {
        Organization org = orgRepo.findById(organizationId).orElseThrow(() -> new RuntimeException("Organization not found"));

        if(!org.getOwner().getId().equals(dto.getOwnerId())){
            throw new RuntimeException("Only the owner can update the organization data.");
        }

        org.setName(dto.getName());
        org.setDescription(dto.getDescription());

        Organization updated = orgRepo.save(org);

        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteOrganization(Long organizationId) {
        orgRepo.deleteById(organizationId);

    }
}
