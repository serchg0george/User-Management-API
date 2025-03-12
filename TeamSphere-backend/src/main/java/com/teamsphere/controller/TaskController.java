package com.teamsphere.controller;

import com.teamsphere.dto.task.TaskDto;
import com.teamsphere.dto.task.TaskSearchRequest;
import com.teamsphere.dto.task.TaskSearchResponse;
import com.teamsphere.exception.NotFoundException;
import com.teamsphere.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.teamsphere.exception.Constants.CREATE_SUCCESS;
import static com.teamsphere.exception.Constants.UPDATE_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/task")
@Validated
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/search")
    public ResponseEntity<TaskSearchResponse> searchTask(@RequestBody TaskSearchRequest findTask) {
        return ResponseEntity.ok(taskService.findTask(findTask));
    }

    @PostMapping
    public ResponseEntity<String> createTask(@Valid @RequestBody TaskDto task) {
        taskService.save(task);
        return ResponseEntity.ok(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long taskId) {
        return ResponseEntity.ok(taskService.get(taskId));
    }

    @GetMapping
    public ResponseEntity<Page<TaskDto>> getAllTasks(Pageable pageable) {
        return ResponseEntity.ok(taskService.getAll(pageable));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateTask(@PathVariable("id") Long taskId,
                                             @Valid @RequestBody TaskDto task) {
        taskService.update(task, taskId);
        return ResponseEntity.ok(UPDATE_SUCCESS);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId) {
        try {
            taskService.delete(taskId);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
