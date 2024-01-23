package com.todoapi.data.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.todoapi.data.entity.Task}
 */
public record TaskDto(String name, String description, Date deadline, boolean isDone) implements Serializable {
}