package com.example.mavbackend.service.interfac;

import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.dto.SignUpDTO;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    Boolean existsUsername(String username);

    UserDTO signUp(SignUpDTO userDto);

    Boolean validateUserSignUp(String email, String document);

    UserDTO createMinistryUser(SignUpDTO user);
}
