package com.teamsphere.service;

import com.teamsphere.dto.task.TaskDto;
import com.teamsphere.dto.task.TaskSearchRequest;
import com.teamsphere.dto.task.TaskSearchResponse;
import com.teamsphere.entity.TaskEntity;

public interface TaskService extends GenericService<TaskEntity, TaskDto> {

    TaskSearchResponse findTask(TaskSearchRequest request);

}
