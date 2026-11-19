package com.devtiro.task.domain;

import com.devtiro.task.entity.TaskPriority;

import java.time.LocalDate;

/**
 * Models a request to create a new task
 * Class is owned by the service layer
 *
 * @param title
 * @param description
 * @param dueDate
 * @param priority
 */
public record CreateTaskRequest(String title,
                                String description,
                                LocalDate dueDate,
                                TaskPriority priority) {}