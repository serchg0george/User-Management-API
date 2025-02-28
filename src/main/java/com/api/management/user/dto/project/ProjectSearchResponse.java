package com.api.management.user.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSearchResponse {

    private List<ProjectDto> projects;

    private Integer projectCount;

}
