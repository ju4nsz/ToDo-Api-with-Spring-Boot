package com.ju4nsz.todoapi.repository;

import com.ju4nsz.todoapi.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskRepository extends JpaRepository<TaskEntity, Long> {



}
