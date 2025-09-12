package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.organizationMembership.OrganizationMembershipRequestDTO;
import com.example.FinanzApp.dto.organizationMembership.OrganizationMembershipResponseDTO;
import com.example.FinanzApp.mappers.OrganizationMembershipMapper;
import com.example.FinanzApp.model.Organization;
import com.example.FinanzApp.model.OrganizationMembership;
import com.example.FinanzApp.model.User;
import com.example.FinanzApp.repository.IOrganizationMembershipRepository;
import com.example.FinanzApp.repository.IOrganizationRepository;
import com.example.FinanzApp.repository.IUserRepository;
import com.example.FinanzApp.service.IOrganizationMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationMembershipService implements IOrganizationMembershipService {
    @Autowired
    private IOrganizationMembershipRepository membershipRepo;
    @Autowired
    private IOrganizationRepository orgRepo;
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private OrganizationMembershipMapper mapper;


    @Override
    @Transactional
    public OrganizationMembershipResponseDTO inviteUser(Long organizationId, String email, Long inviterId) {
        Organization org = orgRepo.findById(organizationId).orElseThrow(() -> new RuntimeException("Organization not found."));

        //verificar si es el owner quien invita.
        if(!org.getOwner().getId().equals(inviterId)){
            throw new RuntimeException("Only the owner can invite users");
        }

        //verificar si existe el mail que estas por invitar
        if(!userRepo.existsByEmail(email)){
            throw new RuntimeException("User with email " + email + " not found.");
        }
        User userToInvite = userRepo.findByEmail(email);

        //verificar si ya es miembro
        boolean alreadyInvited = membershipRepo.existsByOrganizationIdAndUserId(organizationId, userToInvite.getId());
        if(alreadyInvited){
            throw new RuntimeException("User already invited or is already a member.");
        }

        //verificar que no invito al owner
        if(org.getOwner().getId().equals(userToInvite.getId())){
            throw new RuntimeException("Owner cannot be invited as member.");
        }

        OrganizationMembership membership = new OrganizationMembership();
        membership.setOrganization(org);
        membership.setUser(userToInvite);
        membership.setAccepted(false);

        OrganizationMembership saved = membershipRepo.save(membership);

        return mapper.toResponse(saved);
    }

    @Override
    public List<OrganizationMembershipResponseDTO> getPendingInvitationsByUser(Long userId) {
        return membershipRepo.findByUserIdAndAcceptedFalse(userId).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrganizationMembershipResponseDTO respondToInvitation(Long invitationId, OrganizationMembershipRequestDTO dto) {
        OrganizationMembership membership = membershipRepo.findById(invitationId).orElseThrow(() -> new RuntimeException("Invitation not found"));

        //verificar que el usuario que responde es invitado.
        if(!membership.getUser().getId().equals(dto.getUserId())){
            throw new RuntimeException("Only the invited user can respond to the invitation.");
        }
        membership.setAccepted(dto.isAccepted());
        OrganizationMembership updated = membershipRepo.save(membership);

        return mapper.toResponse(updated);
    }

    @Override
    public List<OrganizationMembershipResponseDTO> getMembersByOrganization(Long organizationId) {
        return membershipRepo.findByOrganizationId(organizationId).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeMember(Long organizationId, Long userId, Long removerId) {
        Organization org = orgRepo.findById(organizationId).orElseThrow(() -> new RuntimeException("Organization not found"));

        //verificar que el que elimina es el owner
        if(!org.getOwner().getId().equals(removerId)){
            throw new RuntimeException("Only the owner can remove members");
        }

        //verificar que no se esta intentando eliminar al owner
        if(org.getOwner().getId().equals(userId)){
            throw new RuntimeException("Cannot remove the owner from the organization");
        }

        OrganizationMembership membership = membershipRepo.findByOrganizationIdAndUserId(organizationId, userId).orElseThrow(() -> new RuntimeException("Membership not found."));
        membershipRepo.delete(membership);

    }

    @Override
    public boolean isUserMemberOfOrganization(Long userId, Long organizationId) {
        return membershipRepo.existsByOrganizationIdAndUserIdAndAcceptedTrue(organizationId, userId);
    }

    @Override
    public List<OrganizationMembershipResponseDTO> getOrganizationsByMember(Long userId) {
        return membershipRepo.findByUserIdAndAcceptedTrue(userId).stream().map(mapper::toResponse).collect(Collectors.toList());
    }
}
