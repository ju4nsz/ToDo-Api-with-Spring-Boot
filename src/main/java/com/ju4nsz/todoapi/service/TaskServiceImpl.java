package com.ju4nsz.todoapi.service;

import com.ju4nsz.todoapi.dto.TaskCreateDTO;
import com.ju4nsz.todoapi.dto.TaskDTO;
import com.ju4nsz.todoapi.entity.TaskEntity;
import com.ju4nsz.todoapi.entity.UserEntity;
import com.ju4nsz.todoapi.mapper.ITaskMapper;
import com.ju4nsz.todoapi.repository.ITaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService {

    private final ITaskRepository taskRepository;
    private final ITaskMapper taskMapper;
    private final IUserService iUserService;

    public TaskServiceImpl(ITaskRepository taskRepository, ITaskMapper taskMapper, IUserService iUserService) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.iUserService = iUserService;
    }

    @Override
    public TaskDTO save(TaskCreateDTO taskCreateDTO) {
        if (iUserService.findById(taskCreateDTO.getUserId()).isPresent()){
            return taskMapper.toTaskDto(taskRepository.save(taskMapper.toTaskEntity(taskCreateDTO)));
        }
        return null;
    }

    @Override
    public Page<TaskDTO> findAll(Pageable pageable) {
        return taskMapper.toTaskDto(taskRepository.findAll(pageable));
    }

    @Override
    public Optional<TaskDTO> findById(Long id) {

        Optional<TaskEntity> taskEntity = taskRepository.findById(id);

        return taskEntity.map(taskMapper::toTaskDto);
    }

    @Override
    public Optional<TaskDTO> update() {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(id);
        if (taskEntity.isPresent()){
            taskRepository.delete(taskEntity.get());
            return true;
        }
        return false;
    }
}
