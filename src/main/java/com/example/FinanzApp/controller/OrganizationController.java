package com.example.FinanzApp.controller;

import com.example.FinanzApp.dto.organization.OrganizationRequestDTO;
import com.example.FinanzApp.dto.organization.OrganizationResponseDTO;
import com.example.FinanzApp.service.IOrganizationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {
    @Autowired
    private IOrganizationService orgService;

    @PostMapping
    public ResponseEntity<OrganizationResponseDTO> createOrganization(@RequestBody OrganizationRequestDTO dto){
        OrganizationResponseDTO response = orgService.createOrganization(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationResponseDTO> getOrganizationById(@PathVariable Long organizationId){
        OrganizationResponseDTO response = orgService.getOrganizationById(organizationId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrganizationResponseDTO>> getOrganizationsByUserId(@PathVariable Long userId){
        List<OrganizationResponseDTO> responses = orgService.getOrganizationsByUserId(userId);
        return ResponseEntity.ok(responses);

    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDTO>> getAllOrganizations(){
        List<OrganizationResponseDTO> responses = orgService.getAllOrganizations();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{organizationId}")
    public ResponseEntity<OrganizationResponseDTO> updateOrganization(@PathVariable Long organizationId,
                                                                      @RequestBody OrganizationRequestDTO dto){
        OrganizationResponseDTO response = orgService.updateOrganization(organizationId, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{organizationId}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long organizationId){
        orgService.deleteOrganization(organizationId);
        return ResponseEntity.noContent().build();
    }

}
