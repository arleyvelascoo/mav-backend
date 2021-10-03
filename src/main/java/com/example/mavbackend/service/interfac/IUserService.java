package com.example.mavbackend.service.interfac;

import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.mapper.SignUpDTO;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    UserDTO signUp(SignUpDTO userDto);
}
