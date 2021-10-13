package com.example.mavbackend.controller;

import com.example.mavbackend.dto.MinistrySonDTO;
import com.example.mavbackend.dto.PersonDTO;
import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.mapper.MinistryMapper;
import com.example.mavbackend.mapper.PersonMapper;
import com.example.mavbackend.service.interfac.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller of Person
 */

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private IPersonService personService;
    private PersonMapper personMapper;
    private MinistryMapper ministryMapper;

    /**
     * Get individual person by id
     *
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findPersonById(
            @PathVariable(name = "id") Long personId
    ){
        var response = this.personService.findById(personId);
        if(response == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(personMapper.toPersonDTO(response));
    }

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

    @GetMapping("/disciples/all")
    public ResponseEntity<List<PersonDTO>> getAllDisciples(@AuthenticationPrincipal UserDTO userDTO){

        final var personList = this.personService.getAllDisciples(userDTO);
        if (personList == null || personList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(personMapper.toPersonDTOList(personList));
    }

    @GetMapping("/leaders/all")
    public ResponseEntity<List<MinistrySonDTO>> getAllLeaders(@AuthenticationPrincipal UserDTO userDTO){

        final var ministryList = this.personService.getAllLeaders(userDTO);
        if (ministryList == null || ministryList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(ministryMapper.toMinistrySonDTOList(ministryList));
    }


    @GetMapping("")
    public ResponseEntity<PersonDTO> findPersonFromUser(@AuthenticationPrincipal UserDTO userDTO){
        var person = this.personService.findByIdUser(userDTO.getIdUser());
        if(person == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(this.personMapper.toPersonDTO(person));
    }
    /**
     * Save a person
     * @param personDTO -
     */
    @PostMapping
    public ResponseEntity<PersonDTO> save(@AuthenticationPrincipal UserDTO userDTO,@RequestBody PersonDTO personDTO){
        var saved = this.personService.save(personMapper.toPerson(personDTO),userDTO);
        if (saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(personMapper.toPersonDTO(saved), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonDTO> edit(@AuthenticationPrincipal UserDTO userDTO,@RequestBody PersonDTO personDTO){
        final var updated = this.personService.edit(personMapper.toPerson(personDTO),userDTO);
        if (updated == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(personMapper.toPersonDTO(updated), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@AuthenticationPrincipal UserDTO userDTO,@PathVariable(name="id") Long personID){
     this.personService.deleteById(personID,userDTO);
     return ResponseEntity.ok(true);
    }


    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Autowired
    public void setMinistryMapper(MinistryMapper ministryMapper) {
        this.ministryMapper = ministryMapper;
    }

    @Autowired
    public void setPersonService(@Qualifier("principalPersonService") IPersonService personService) {
        this.personService = personService;
    }
}
