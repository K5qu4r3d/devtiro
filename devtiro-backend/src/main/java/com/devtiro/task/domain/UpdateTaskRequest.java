package com.devtiro.task.domain;

import com.devtiro.task.entity.TaskPriority;
import com.devtiro.task.entity.TaskStatus;

import java.time.LocalDate;

public record UpdateTaskRequest(
        String title,
        String description,
        LocalDate dueDate,
        TaskStatus status,
        TaskPriority priority
) {}
