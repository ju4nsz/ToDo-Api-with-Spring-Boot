package com.ju4nsz.todoapi.service;

import com.ju4nsz.todoapi.dto.UserCreateDto;
import com.ju4nsz.todoapi.dto.UserDTO;
import com.ju4nsz.todoapi.entity.UserEntity;
import com.ju4nsz.todoapi.mapper.IUserMapper;
import com.ju4nsz.todoapi.repository.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository, IUserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO save(UserCreateDto userCreateDto) {
        userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        return userMapper.toUserDto(userRepository.save(userMapper.toUserEntity(userCreateDto)));
    }

    @Override
    public Page<UserDTO> findAll(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        return userMapper.toUserDto(userRepository.findAll(pageable));
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(userMapper::toUserDto);
    }


    @Override
    public Optional<UserDTO> update(UserDTO userDTO) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()){
            userRepository.delete(userEntity.get());
            return true;
        }
        return false;
    }

}
