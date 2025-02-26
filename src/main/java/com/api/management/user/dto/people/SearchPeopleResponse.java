package com.api.management.user.dto.people;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchPeopleResponse {

    private List<PeopleDto> people;

    private Integer peopleCount;
}
