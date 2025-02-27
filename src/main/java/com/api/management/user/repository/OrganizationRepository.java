package com.api.management.user.repository;

import com.api.management.user.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<CompanyEntity, Long> {
}
