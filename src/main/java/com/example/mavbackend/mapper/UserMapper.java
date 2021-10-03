package com.example.mavbackend.mapper;

import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDTO signUpDTO);

    @Mapping(target = "token", ignore = true)
    UserDTO toUserDTO(User user);
}
