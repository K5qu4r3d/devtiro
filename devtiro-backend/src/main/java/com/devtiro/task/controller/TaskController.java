package com.devtiro.task.controller;

import com.devtiro.task.domain.CreateTaskRequest;
import com.devtiro.task.domain.Task;
import com.devtiro.task.domain.UpdateTaskRequest;
import com.devtiro.task.domain.dto.CreateTaskRequestDto;
import com.devtiro.task.domain.dto.TaskDto;
import com.devtiro.task.domain.dto.UpdateTaskRequestDto;
import com.devtiro.task.mapper.TaskMapper;
import com.devtiro.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(
            @Valid @RequestBody CreateTaskRequestDto request
    ) {
        CreateTaskRequest taskToCreate = taskMapper.fromDto(request);
        Task createdTask = taskService.createTask(taskToCreate);
        TaskDto createdTaskDto = taskMapper.toDto(createdTask);
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks() {
        List<Task> tasks = taskService.listTasks();

        // Map Task objects to TaskDto objects
        List<TaskDto> taskDtoList = tasks.stream()
                .map(taskMapper::toDto)
                .toList();

        return ResponseEntity.ok(taskDtoList);
    }

    /**
     * Update specified task with provided information
     * @param id
     * @param dto request DTO used to update the task
     * @return representation of updated task with HTTP 200
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTaskRequestDto dto
    ) {
        UpdateTaskRequest request = taskMapper.fromDto(dto);
        Task updatedTask = taskService.updateTask(id, request);
        TaskDto taskMapperDto = taskMapper.toDto(updatedTask);
        return ResponseEntity.ok(taskMapperDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
