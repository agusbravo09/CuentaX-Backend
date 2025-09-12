package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.OrganizationMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrganizationMembershipRepository extends JpaRepository<OrganizationMembership, Long> {
    List<OrganizationMembership> findByUserIdAndAcceptedFalse(Long userId);
    List<OrganizationMembership> findByOrganizationId(Long organizationId);
    List<OrganizationMembership> findByUserIdAndAcceptedTrue(Long userId);
    Optional<OrganizationMembership> findByOrganizationIdAndUserId(Long organizationId, Long userId);
    boolean existsByOrganizationIdAndUserId(Long organizationId, Long userId);
    boolean existsByOrganizationIdAndUserIdAndAcceptedTrue(Long organizationId, Long userId);
}
