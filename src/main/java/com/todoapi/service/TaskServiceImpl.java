package com.todoapi.service;

import com.todoapi.data.dto.TaskDto;
import com.todoapi.data.entity.Task;
import com.todoapi.data.mapper.TaskMapper;
import com.todoapi.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "allTasks")
    public List<TaskDto> listTasks(Optional<Boolean> status) {
        System.out.println("Cache miss");
        return status.isPresent() ? getTasks(status) : getTasks();
    }

    @Override
    @Transactional
    @CacheEvict(value = "allTasks", allEntries = true)
    public void addTask(TaskDto taskDto) {
        System.out.println("Cache evict");
        taskRepository.save(TaskMapper.INSTANCE.toEntity(taskDto));
    }

    @Override
    @Transactional
    @CacheEvict(value = "allTasks", allEntries = true)
    public void updateStatusTask(Long id, boolean status) {
        System.out.println("Cache evict");
        Task task = taskRepository.getReferenceById(id);
        task.setDone(status);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allTasks", allEntries = true)
    public void deleteTask(Long id) {
        System.out.println("Cache evict");
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
