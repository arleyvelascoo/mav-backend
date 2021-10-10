package com.example.mavbackend.service.interfac;

import com.example.mavbackend.dto.SignUpDTO;
import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.model.Ministry;
import com.example.mavbackend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service interface of Ministry
 */

@Service
public interface IMinistryService {

    Page<Ministry> getAll(Pageable pageable);

    Ministry save(Ministry ministry);

    Ministry registerPerson(UserDTO user , Ministry ministry);

    Boolean validateMinistrySignUp(String email, String document);

}
