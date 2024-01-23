package com.todoapi.controller;

import com.todoapi.data.dto.TaskDto;
import com.todoapi.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> listTasks(@RequestParam(value = "isDone", required = false) Boolean isDone){
        return new ResponseEntity<>(taskService.listTasks(Optional.ofNullable(isDone)), HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto){
        taskService.addTask(taskDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> changeStatusTask(@PathVariable Long id, @RequestParam boolean isDone){
        taskService.updateStatusTask(id, isDone);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
