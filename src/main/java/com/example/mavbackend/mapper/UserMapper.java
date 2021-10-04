package com.example.mavbackend.mapper;

import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.model.User;
import com.example.mavbackend.model.UserRol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDTO signUpDTO);

    @Mapping(target = "rolName", ignore = true)
    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "idRol", ignore = true)
    @Mapping(target = "token", ignore = true)
    UserDTO toUserDTO(User user);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "rolName", source = "rol.name")
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "id", source = "user.id")
    UserDTO toUserDTOFromUserRol(UserRol userRol);
}
