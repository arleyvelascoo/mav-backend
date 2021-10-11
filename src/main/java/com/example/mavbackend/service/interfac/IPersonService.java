package com.example.mavbackend.service.interfac;

import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service interface of Person
 */

@Service
public interface IPersonService {

    Page<Person> getAll(Pageable pageable);

    Person findByIdUser(Long userId);

    Person save(Person person,UserDTO userDTO);

    Person edit(Person person, UserDTO userDTO);

    void deleteById(Long personID,UserDTO userDTO);

    Person findById(Long personId);
}
