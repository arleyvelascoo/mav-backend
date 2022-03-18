package com.example.mavbackend.service.implementation;

import com.example.mavbackend.dto.CredentialsDTO;
import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.exception.MAVValidationException;
import com.example.mavbackend.mapper.UserMapper;
import com.example.mavbackend.repository.IPersonRepository;
import com.example.mavbackend.repository.IUserRepository;
import com.example.mavbackend.repository.IUserRolRepository;
import com.example.mavbackend.service.interfac.IPersonService;
import com.example.mavbackend.service.interfac.ISendEmailService;
import com.example.mavbackend.util.PasswordGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private final IUserRepository userRepository;
    private final IUserRolRepository userRolRepository;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ISendEmailService sendEmailService;
    private final IPersonRepository personRepository;

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

    public void verifyIdentityAndSendEmail(String username) {
        var user = this.userRepository.findTopByUsername(username);
        if (user == null)
            throw new MAVValidationException("This username doesn't exists");
        var person = this.personRepository.findTopByUserId(user.getId());
        if (person == null)
            throw new MAVValidationException("There's not a person with this user");

        var newPassword = PasswordGenerator.getPassword(7);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(newPassword)));

        this.userRepository.save(user);

        var asunto = "Password reminder MCI";
        var texto = "La contraseña para el usuario: " + username + " es: " + newPassword;
        var footer = "";
        List<String> destinatarios = new ArrayList<>();

        if (!person.getEmail().isEmpty())
            destinatarios.add(person.getEmail());
        Map<String, String> inlineImages = new HashMap<>();
//        inlineImages.put("logo", "img/logo-01.png");
//        inlineImages.put("notificacion", "img/Notificacion-01.png");

        this.sendEmailService.sendSimpleEmail(asunto, texto, footer, destinatarios, inlineImages);
    }

}
