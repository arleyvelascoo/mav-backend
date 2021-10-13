package com.example.mavbackend.service.implementation;

import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.exception.MAVValidationException;
import com.example.mavbackend.dto.SignUpDTO;
import com.example.mavbackend.mapper.UserMapper;
import com.example.mavbackend.model.User;
import com.example.mavbackend.repository.IMinistryRepository;
import com.example.mavbackend.repository.IPersonRepository;
import com.example.mavbackend.repository.IUserRepository;
import com.example.mavbackend.service.interfac.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final IPersonRepository personRepository;
    private final IMinistryRepository ministryRepository;

    @Override
    public Boolean existsUsername(String username){
        return this.userRepository.findTopByUsername(username) != null;
    }


    @Override
    public UserDTO signUp(SignUpDTO userDto) {
        var optionalUser = this.userRepository.findTopByUsername(userDto.getUsername());

        if (optionalUser != null) {
            throw new MAVValidationException("Login already exists");
        }

        var toSignUp = this.personRepository.findByEmailAndDocumentNumber(userDto.getEmail(),userDto.getDocument());

        if(toSignUp==null)
            throw new MAVValidationException("Usuario no registrado como persona");

        if(toSignUp.getUserId() != null)
            throw new MAVValidationException("Ya tiene Usuario registrado.");

        var user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        var savedUser = userRepository.save(user);

        return userMapper.toUserDTO(savedUser);
    }

    @Override
    public Boolean validateUserSignUp(String email, String document) {
        return this.personRepository.findByDocumentNumberAndEmail(document,email)!=null;
    }

    @Override
    public UserDTO createMinistryUser(SignUpDTO user) {
        var person = this.personRepository.findByEmailAndDocumentNumber(user.getEmail(),user.getDocument());
        if(person == null) throw new MAVValidationException("No hay registro en la base de datos.");

        var ministry  = this.ministryRepository.findByIdPersonToCreate(person.getId());
        if(ministry == null) throw new MAVValidationException("No se encuentra asignado a un ministerio pendiente, por favor" +
                "comuniquese con su líder para verificar el proceso de registro");
        var userCreated = this.signUp(user);

        if(userCreated == null) throw new MAVValidationException("No se pudo crear el usuario.");
        ministry.setIdUser(userCreated.getIdUser());
        this.ministryRepository.save(ministry);
        return userCreated;
    }

}
