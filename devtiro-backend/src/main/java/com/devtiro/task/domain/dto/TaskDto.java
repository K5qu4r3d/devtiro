package com.devtiro.task.domain.dto;

import com.devtiro.task.entity.TaskPriority;
import com.devtiro.task.entity.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO representing a task
 * Class owned by presentation layer
 * @param id
 * @param title
 * @param description
 * @param dueDate
 * @param priority
 * @param status
 */
public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority,
        TaskStatus status
) {}
