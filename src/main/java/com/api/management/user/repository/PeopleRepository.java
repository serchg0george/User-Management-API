package com.api.management.user.repository;

import com.api.management.user.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findAllByFullNameIgnoreCase(String fullName);

}
