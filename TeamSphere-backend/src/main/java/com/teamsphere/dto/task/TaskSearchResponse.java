package com.teamsphere.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskSearchResponse {

    private List<TaskDto> tasks;

    private Integer taskCount;

}
