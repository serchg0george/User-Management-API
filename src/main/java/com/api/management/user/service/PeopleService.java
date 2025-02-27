package com.api.management.user.service;

import com.api.management.user.dto.people.PeopleDto;
import com.api.management.user.dto.people.SearchPeopleResponse;
import com.api.management.user.dto.search.PeopleSearchRequest;
import com.api.management.user.entity.EmployeeEntity;

public interface PeopleService extends GenericService<EmployeeEntity, PeopleDto> {

    PeopleDto setMailToPeople(Long mailId, Long peopleId);

    SearchPeopleResponse findPersonByCriteria(PeopleSearchRequest request);

}
