/*
 * Main properties which transferred between services and shown to client
 */

package com.todoapi.data.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.todoapi.data.entity.Task}
 */
public record TaskDto(@NotBlank(message = "Name cannot be empty") String name, @NotBlank(message = "Description cannot be empty") String description, Date deadline, boolean isDone) implements Serializable {
}