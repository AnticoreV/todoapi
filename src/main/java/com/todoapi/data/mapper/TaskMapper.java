/*
 * Mapping interface to map from entity to DTO and vice versa
 */

package com.todoapi.data.mapper;

import com.todoapi.data.dto.TaskDto;
import com.todoapi.data.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "lombok")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    @Mapping(source = "done", target = "isDone")
    TaskDto toApi(Task source);
    Task toEntity(TaskDto taskDto);
}
