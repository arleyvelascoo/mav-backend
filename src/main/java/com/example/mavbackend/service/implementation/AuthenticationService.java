package com.example.mavbackend.service.implementation;

import com.example.mavbackend.dto.CredentialsDTO;
import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.exception.MAVValidationException;
import com.example.mavbackend.mapper.UserMapper;
import com.example.mavbackend.repository.IUserRepository;
import com.example.mavbackend.repository.IUserRolRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.CharBuffer;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private final IUserRepository userRepository;
    private final IUserRolRepository userRolRepository;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO authenticate(CredentialsDTO credentialsDTO) {
        var userDB = this.userRepository.findTopByUsername(credentialsDTO.getUsername());

        if (userDB == null) throw new MAVValidationException("Username not found");

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.getPassword()),
                userDB.getPassword())) {
            var userRol = this.userRolRepository.findTopByIdUserAndIdRol(userDB.getId(),
                    credentialsDTO.getIdRol());
            if (userRol == null) {
                throw new MAVValidationException("This rol doesn't exists");
            }
            return this.userMapper.toUserDTOFromUserRol(userRol);
        }

        throw new MAVValidationException("Invalid password");
    }

    public UserDTO findByLogin(Long idUser, Long idRol) {
        var userRol = this.userRolRepository.findTopByIdUserAndIdRol(idUser,
                idRol);
        if (userRol == null) {
            throw new MAVValidationException("This rol doesn't exists");
        }
        return this.userMapper.toUserDTOFromUserRol(userRol);
    }
}
