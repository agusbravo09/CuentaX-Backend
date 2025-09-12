package com.example.FinanzApp.dto.organizationMembership;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrganizationMembershipRequestDTO {
    private Long userId;
    private boolean accepted;
}
