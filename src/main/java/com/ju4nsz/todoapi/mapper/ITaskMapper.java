package com.ju4nsz.todoapi.mapper;

import com.ju4nsz.todoapi.dto.TaskCreateDTO;
import com.ju4nsz.todoapi.dto.TaskDTO;
import com.ju4nsz.todoapi.entity.TaskEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITaskMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "finished", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "deadline", ignore = true)
    TaskEntity toTaskEntity(TaskCreateDTO taskCreateDTO);

    @InheritInverseConfiguration
    @Mapping(source = "id", target = "id")
    @Mapping(source = "finished", target = "finished")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "status", target = "status")
    @Mapping(target = "userId", ignore = true)
    @Mapping(source = "deadline", target = "deadline")
    TaskDTO toTaskDto(TaskEntity taskEntity);

    List<TaskDTO> toTaskDto(List<TaskEntity> taskEntityList);

    Page<TaskDTO> toTaskDto(Page<TaskEntity> taskEntityPage);

}
