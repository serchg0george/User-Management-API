package com.api.management.user.repository;

import com.api.management.user.entity.TimeTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTrackingRepository extends JpaRepository<TimeTrackingEntity, Long> {
}
