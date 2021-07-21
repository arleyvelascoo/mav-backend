package com.example.mavbackend.controller;

import com.example.mavbackend.dto.PersonDTO;
import com.example.mavbackend.mapper.PersonMapper;
import com.example.mavbackend.service.interfac.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of Person
 */

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private IPersonService personService;
    private PersonMapper personMapper;

    /**
     * Gets all registers of Person with pagination and sorting
     * @param pageable - Instance of Pageable
     */
    @GetMapping("/all")
    public ResponseEntity<Page<PersonDTO>> getAll(Pageable pageable) {
        final var personList = this.personService.getAll(pageable);
        if (personList == null || personList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(personList.map(p -> this.personMapper.toPersonDTO(p)), HttpStatus.OK);
    }

    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Autowired
    public void setPersonService(@Qualifier("principalPersonService") IPersonService personService) {
        this.personService = personService;
    }
}
