package com.example.mavbackend.service.implementation;

import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.exception.MAVValidationException;
import com.example.mavbackend.dto.SignUpDTO;
import com.example.mavbackend.mapper.UserMapper;
import com.example.mavbackend.model.User;
import com.example.mavbackend.repository.IUserRepository;
import com.example.mavbackend.service.interfac.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO signUp(SignUpDTO userDto) {
        var optionalUser = this.userRepository.findTopByUsername(userDto.getUsername());

        if (optionalUser != null) {
            throw new MAVValidationException("Login already exists");
        }

        var user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        var savedUser = userRepository.save(user);

        return userMapper.toUserDTO(savedUser);
    }
}
