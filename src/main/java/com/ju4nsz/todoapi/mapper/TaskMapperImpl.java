package com.ju4nsz.todoapi.mapper;

import com.ju4nsz.todoapi.dto.TaskCreateDTO;
import com.ju4nsz.todoapi.dto.TaskDTO;
import com.ju4nsz.todoapi.entity.TaskEntity;
import com.ju4nsz.todoapi.repository.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapperImpl implements ITaskMapper {

    private final IUserRepository iUserRepository;

    public TaskMapperImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public TaskEntity toTaskEntity(TaskCreateDTO taskCreateDTO) {
        if (taskCreateDTO == null) {
            return null;
        }
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskCreateDTO.getTitle());
        taskEntity.setDescription(taskCreateDTO.getDescription());
        taskEntity.setUser(iUserRepository.findById(taskCreateDTO.getUserId()).get());
        return taskEntity;
    }

    @Override
    public TaskDTO toTaskDto(TaskEntity taskEntity) {
        if (taskEntity == null) {
            return null;
        }
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(taskEntity.getId());
        taskDTO.setTitle(taskEntity.getTitle());
        taskDTO.setDescription(taskEntity.getDescription());
        taskDTO.setDeadline(taskEntity.getDeadline());
        taskDTO.setCreatedAt(taskEntity.getCreatedAt());
        taskDTO.setFinished(taskEntity.isFinished());
        taskDTO.setStatus(taskEntity.getStatus());
        taskDTO.setUserId(taskEntity.getUser().getId());
        return taskDTO;
    }

    @Override
    public List<TaskDTO> toTaskDto(List<TaskEntity> taskEntityList) {
        if (taskEntityList == null) {
            return Collections.emptyList();
        }
        return taskEntityList.stream()
                .map(this::toTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<TaskDTO> toTaskDto(Page<TaskEntity> taskEntityPage) {
        if (taskEntityPage == null){
            return null;
        }
        return taskEntityPage.map(this::toTaskDto);
    }
}
