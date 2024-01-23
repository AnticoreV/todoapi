package com.todoapi.service;

import com.todoapi.data.dto.TaskDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<TaskDto> listTasks(Optional<Boolean> status);
    void addTask(TaskDto taskDto);
    void updateStatusTask(Long id, boolean status);
    void deleteTask(Long id);
}
