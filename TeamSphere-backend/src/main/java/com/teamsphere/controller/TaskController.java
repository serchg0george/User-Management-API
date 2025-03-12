package com.teamsphere.controller;

import com.teamsphere.dto.task.TaskDto;
import com.teamsphere.dto.task.TaskSearchRequest;
import com.teamsphere.dto.task.TaskSearchResponse;
import com.teamsphere.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.teamsphere.exception.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

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
        return ok().body(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long taskId) {
        return new ResponseEntity<>(taskService.get(taskId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<TaskDto>> getAllTasks(Pageable pageable) {
        return new ResponseEntity<>(taskService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateTask(@PathVariable("id") Long taskId,
                                             @Valid @RequestBody TaskDto task) {
        taskService.update(task, taskId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
    }
}
