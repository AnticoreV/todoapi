package com.todoapi.controller;

import com.todoapi.data.dto.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> listTasks(@RequestParam(value = "isDone", required = false) boolean isDone){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> changeStatusTask(@PathVariable Long id, @RequestParam boolean isDone){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
