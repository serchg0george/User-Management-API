package com.teamsphere.dto.company;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CompanyCreatedResponse {
    private String name;
    private String industry;
    private String address;
    private String email;
}
