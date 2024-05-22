package com.ju4nsz.todoapi.service;

import com.ju4nsz.todoapi.dto.UserCreateDto;
import com.ju4nsz.todoapi.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService {

    UserDTO save(UserCreateDto userCreateDto);

    Page<UserDTO> findAll(Pageable pageable);

    Optional<UserDTO> findById(Long id);

    Optional<UserDTO> update(UserDTO userDTO);

    boolean delete(Long id);

}
