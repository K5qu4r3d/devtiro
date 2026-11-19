package com.devtiro.task.mapper;

import com.devtiro.task.domain.CreateTaskRequest;
import com.devtiro.task.domain.Task;
import com.devtiro.task.domain.UpdateTaskRequest;
import com.devtiro.task.domain.dto.CreateTaskRequestDto;
import com.devtiro.task.domain.dto.TaskDto;
import com.devtiro.task.domain.dto.UpdateTaskRequestDto;

public interface TaskMapper {
    CreateTaskRequest fromDto(CreateTaskRequestDto dto);
    TaskDto toDto(Task task);
    UpdateTaskRequest fromDto(UpdateTaskRequestDto dto);
}
