package com.devtiro.task.service;

import com.devtiro.task.domain.CreateTaskRequest;
import com.devtiro.task.domain.Task;
import com.devtiro.task.domain.UpdateTaskRequest;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Task createTask(CreateTaskRequest request);
    List<Task> listTasks();
    Task updateTask(UUID id, UpdateTaskRequest request);
    void deleteTask(UUID id);
}
