package com.ju4nsz.todoapi.mapper;

import com.ju4nsz.todoapi.dto.UserCreateDto;
import com.ju4nsz.todoapi.dto.UserDTO;
import com.ju4nsz.todoapi.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "age", target = "age")
    UserEntity toUserEntity(UserCreateDto userCreateDto);

    @InheritInverseConfiguration
    @Mapping(source = "role", target = "role")
    @Mapping(source = "id", target = "id")
    UserDTO toUserDto(UserEntity userEntity);

    List<UserDTO> toUserDto(List<UserEntity> userEntityList);

    Page<UserDTO> toUserDto(Page<UserEntity> userEntityPage);

}
