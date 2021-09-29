package com.example.mavbackend.controller;

import com.example.mavbackend.dto.PersonDTO;
import com.example.mavbackend.mapper.PersonMapper;
import com.example.mavbackend.service.interfac.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Save a person
     * @param personDTO -
     */
    @PostMapping
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO personDTO){
        var saved = this.personService.save(personMapper.toPerson(personDTO));
        if (saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(personMapper.toPersonDTO(saved), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonDTO> edit(@RequestBody PersonDTO personDTO){
        final var updated = this.personService.edit(personMapper.toPerson(personDTO));
        if (updated == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(personMapper.toPersonDTO(updated), HttpStatus.OK);
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
