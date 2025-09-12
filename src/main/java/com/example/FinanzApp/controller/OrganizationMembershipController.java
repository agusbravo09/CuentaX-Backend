package com.example.FinanzApp.controller;

import com.example.FinanzApp.dto.organizationMembership.OrganizationMembershipRequestDTO;
import com.example.FinanzApp.dto.organizationMembership.OrganizationMembershipResponseDTO;
import com.example.FinanzApp.service.IOrganizationMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/memberships")
public class OrganizationMembershipController {
    @Autowired
    private IOrganizationMembershipService membershipService;

    // Invitar usuario por email
    @PostMapping("/organizations/{organizationId}/invite")
    public ResponseEntity<OrganizationMembershipResponseDTO> inviteUser(@PathVariable Long organizationId, @RequestParam String email, @RequestParam Long inviterId) {
        OrganizationMembershipResponseDTO invitation = membershipService.inviteUser(organizationId, email, inviterId);
        return ResponseEntity.ok(invitation);
    }

    // Obtener invitaciones pendientes de un usuario
    @GetMapping("/users/{userId}/pending-invitations")
    public ResponseEntity<List<OrganizationMembershipResponseDTO>> getPendingInvitations(@PathVariable Long userId) {
        List<OrganizationMembershipResponseDTO> invitations = membershipService.getPendingInvitationsByUser(userId);
        return ResponseEntity.ok(invitations);
    }

    // Responder a una invitación (aceptar/rechazar)
    @PutMapping("/invitations/{invitationId}/respond")
    public ResponseEntity<OrganizationMembershipResponseDTO> respondToInvitation(@PathVariable Long invitationId, @RequestBody OrganizationMembershipRequestDTO requestDTO) {
        OrganizationMembershipResponseDTO response = membershipService.respondToInvitation(invitationId, requestDTO);
        return ResponseEntity.ok(response);
    }

    // Obtener todos los miembros de una organización
    @GetMapping("/organizations/{organizationId}/members")
    public ResponseEntity<List<OrganizationMembershipResponseDTO>> getOrganizationMembers(@PathVariable Long organizationId) {
        List<OrganizationMembershipResponseDTO> members = membershipService.getMembersByOrganization(organizationId);
        return ResponseEntity.ok(members);
    }

    // Remover un miembro de una organización
    @DeleteMapping("/organizations/{organizationId}/members/{userId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long organizationId, @PathVariable Long userId,@RequestParam Long removerId) {
        membershipService.removeMember(organizationId, userId, removerId);
        return ResponseEntity.noContent().build();
    }

    // Verificar si un usuario es miembro de una organización
    @GetMapping("/organizations/{organizationId}/members/{userId}/check")
    public ResponseEntity<Boolean> checkUserMembership(@PathVariable Long organizationId, @PathVariable Long userId) {
        boolean isMember = membershipService.isUserMemberOfOrganization(userId, organizationId);
        return ResponseEntity.ok(isMember);
    }

    // Obtener todas las organizaciones a las que pertenece un usuario
    @GetMapping("/users/{userId}/organizations")
    public ResponseEntity<List<OrganizationMembershipResponseDTO>> getUserOrganizations(@PathVariable Long userId) {
        List<OrganizationMembershipResponseDTO> organizations = membershipService.getOrganizationsByMember(userId);
        return ResponseEntity.ok(organizations);
    }
}
