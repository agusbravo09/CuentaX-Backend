package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.organizationMembership.OrganizationMembershipRequestDTO;
import com.example.FinanzApp.dto.organizationMembership.OrganizationMembershipResponseDTO;

import java.util.List;

public interface IOrganizationMembershipService {
    OrganizationMembershipResponseDTO inviteUser(Long organizationId, String email, Long inviterId);
    List<OrganizationMembershipResponseDTO> getPendingInvitationsByUser(Long userId);
    OrganizationMembershipResponseDTO respondToInvitation(Long invitationId, OrganizationMembershipRequestDTO dto);
    List<OrganizationMembershipResponseDTO> getMembersByOrganization(Long organizationId);
    void removeMember(Long organizationId, Long userId, Long removerId);
    boolean isUserMemberOfOrganization(Long userId, Long organizationId);
    List<OrganizationMembershipResponseDTO> getOrganizationsByMember(Long userId);
}
