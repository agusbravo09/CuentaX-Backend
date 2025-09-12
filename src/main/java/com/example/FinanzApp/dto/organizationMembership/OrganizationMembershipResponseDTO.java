package com.example.FinanzApp.dto.organizationMembership;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrganizationMembershipResponseDTO {
    private Long id;
    private String organizationName;
    private String userName;
    private String userEmail;
    private boolean accepted;
}
