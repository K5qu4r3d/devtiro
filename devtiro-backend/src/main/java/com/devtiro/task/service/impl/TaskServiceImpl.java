package com.devtiro.task.service.impl;

import com.devtiro.task.domain.CreateTaskRequest;
import com.devtiro.task.domain.Task;
import com.devtiro.task.domain.UpdateTaskRequest;
import com.devtiro.task.entity.TaskStatus;
import com.devtiro.task.exception.TaskNotFoundException;
import com.devtiro.task.repository.TaskRepository;
import com.devtiro.task.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(CreateTaskRequest request) {
        Instant now = Instant.now();

        Task newTask = new Task(
                null,
                request.title(),
                request.description(),
                request.dueDate(),
                TaskStatus.OPEN,
                request.priority(),
                now,
                now
        );

        // Save task and return saved task to caller
        return taskRepository.save(newTask);
    }

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC, "created"));
    }

    @Override
    public Task updateTask(UUID id, UpdateTaskRequest request) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        // Update task with new information
        existingTask.setTitle(request.title());
        existingTask.setDescription(request.description());
        existingTask.setDueDate(request.dueDate());
        existingTask.setPriority(request.priority());
        existingTask.setStatus(request.status());

        // Update task's 'updated' field
        existingTask.setUpdated(Instant.now());

        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}
