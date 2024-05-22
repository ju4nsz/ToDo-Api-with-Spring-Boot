package com.ju4nsz.todoapi.service;

import com.ju4nsz.todoapi.dto.TaskCreateDTO;
import com.ju4nsz.todoapi.dto.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ITaskService {

    TaskDTO save(TaskCreateDTO taskCreateDTO);

    Page<TaskDTO> findAll(Pageable pageable);

    Optional<TaskDTO> findById(Long id);

    Optional<TaskDTO> update();

    boolean delete(Long id);

}
