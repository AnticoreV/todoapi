package com.todoapi.service;

import com.todoapi.data.dto.TaskDto;
import com.todoapi.data.entity.Task;
import com.todoapi.data.mapper.TaskMapper;
import com.todoapi.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> listTasks(Optional<Boolean> status) {
        return status.isPresent() ? getTasks(status) : getTasks();
    }

    @Override
    @Transactional
    public void addTask(TaskDto taskDto) {
        taskRepository.save(TaskMapper.INSTANCE.toEntity(taskDto));
    }

    @Override
    @Transactional
    public void updateStatusTask(Long id, boolean status) {
        Task task = taskRepository.getReferenceById(id);
        task.setDone(status);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private List<TaskDto> getTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper.INSTANCE::toApi)
                .collect(Collectors.toList());
    }

    private List<TaskDto> getTasks(Optional<Boolean> status) {
        return taskRepository.findAll()
                .stream()
                .filter(task -> task.isDone() == status.get())
                .map(TaskMapper.INSTANCE::toApi)
                .collect(Collectors.toList());
    }
}
