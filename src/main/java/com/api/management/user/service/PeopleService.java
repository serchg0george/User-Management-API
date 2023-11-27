package com.api.management.user.service;

import com.api.management.user.dto.PeopleDto;
import com.api.management.user.entity.PeopleEntity;

import java.util.List;

public interface PeopleService extends GenericService<PeopleEntity, PeopleDto> {

    PeopleDto setMailToPeople(Long mailId, Long peopleId);

    List<PeopleDto> searchPeopleByName(String query);

}
