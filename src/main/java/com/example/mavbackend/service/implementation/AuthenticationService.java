package com.example.mavbackend.service.implementation;

import com.example.mavbackend.dto.CredentialsDTO;
import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.exception.MAVValidationException;
import com.example.mavbackend.mapper.UserMapper;
import com.example.mavbackend.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.CharBuffer;
import java.util.Arrays;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO authenticate(CredentialsDTO credentialsDTO) {
        var userDB = this.userRepository.findTopByUsername(credentialsDTO.getUsername());

        if (userDB == null) throw new MAVValidationException("Username not found");

        if (passwordEncoder.matches(CharBuffer.wrap(Arrays.toString(credentialsDTO.getPassword())),
                Arrays.toString(userDB.getPassword()))) {
            return this.userMapper.toUserDTO(userDB);
        }

        throw new MAVValidationException("Invalid password");
    }

    public UserDTO findByLogin(String username) {
        var userDB = this.userRepository.findTopByUsername(username);

        if (userDB == null) throw new MAVValidationException("Username not found");
        return this.userMapper.toUserDTO(userDB);
    }
}
