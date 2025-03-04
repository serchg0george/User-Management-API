package com.teamsphere.dto.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanySearchResponse {

    private List<CompanyDto> companies;

    private Integer companyCount;
}
