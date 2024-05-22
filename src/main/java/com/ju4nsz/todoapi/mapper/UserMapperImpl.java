package com.ju4nsz.todoapi.mapper;

import com.ju4nsz.todoapi.dto.UserCreateDto;
import com.ju4nsz.todoapi.dto.UserDTO;
import com.ju4nsz.todoapi.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements IUserMapper {

    private final ITaskMapper taskMapper;

    public UserMapperImpl(ITaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public UserEntity toUserEntity(UserCreateDto userCreateDto) {
        if (userCreateDto == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userCreateDto.getUsername());
        userEntity.setEmail(userCreateDto.getEmail());
        userEntity.setPassword(userCreateDto.getPassword());
        userEntity.setFullName(userCreateDto.getFullName());
        userEntity.setGender(userCreateDto.getGender());
        userEntity.setPhoneNumber(userCreateDto.getPhoneNumber());
        userEntity.setAge(userCreateDto.getAge());
        return userEntity;
    }

    @Override
    public UserDTO toUserDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setFullName(userEntity.getFullName());
        userDTO.setGender(userEntity.getGender());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userDTO.setAge(userEntity.getAge());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setRole(userEntity.getRole());
        userDTO.setTasks(taskMapper.toTaskDto(userEntity.getTasks()));
        return userDTO;
    }

    @Override
    public List<UserDTO> toUserDto(List<UserEntity> userEntityList) {
        if (userEntityList == null) {
            return null;
        }
        return userEntityList.stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDTO> toUserDto(Page<UserEntity> userEntityPage) {
        if (userEntityPage == null) {
            return null;
        }
        return userEntityPage.map(this::toUserDto);
    }
}
