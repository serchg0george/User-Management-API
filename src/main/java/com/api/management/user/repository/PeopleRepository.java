package com.api.management.user.repository;

import com.api.management.user.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository extends JpaRepository<PeopleEntity, Long> {

    List<PeopleEntity> findAllByFullNameIgnoreCase(String fullName);

}
