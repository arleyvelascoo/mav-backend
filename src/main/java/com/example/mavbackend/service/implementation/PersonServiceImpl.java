package com.example.mavbackend.service.implementation;

import com.example.mavbackend.exception.MAVValidationException;
import com.example.mavbackend.model.Person;
import com.example.mavbackend.repository.*;
import com.example.mavbackend.service.interfac.IPersonService;
import com.example.mavbackend.util.IConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Qualifier("principalPersonService")
@AllArgsConstructor
@Service
public class PersonServiceImpl implements IPersonService {

    private final IPersonRepository personRepository;
    private final EntityManager em;
    private final IDocumentTypeRepository documentTypeRepository;
    private final IGenderRepository genderRepository;
    private final IMinistryRepository ministryRepository;
    private final ICityRepository cityRepository;

    /**
     * Gets entire list of all persons with pagination
     * @param pageable - Instance of Pageable
     */
    @Override
    public Page<Person> getAll(Pageable pageable) {
        return this.personRepository.findAll(pageable);
    }

    /**
     * Save a new Person
     * @param person - Instance of Person entity
     */
    @Override
    @Transactional
    public Person save(Person person) {
        validate(person, IConstants.INSERT_MODE);
        this.personRepository.save(person);
        em.refresh(person);
        return person;
    }

    /**
     * Update a new Person
     * @param person - Instance of Person entity
     */
    @Override
    @Transactional
    public Person edit(Person person){
        validate(person, IConstants.EDIT_MODE);
        this.personRepository.save(person);
        this.setToResponse(person);
        return person;
    }

    /**
     * delete a new Person
     * @param personID - Instance of Person entity
     */
    @Override
    public void deleteById(Long personID) {
        this.personRepository.deleteById(personID);
    }

    @Override
    public Person findById(Long personId) {
        return this.personRepository.findById(personId).orElseThrow(MAVValidationException::new);
    }

    private void validate(Person person, String mode){
        var existing = this.personRepository
                .findTopByDocumentNumberAndIdDocumentType(person.getDocumentNumber(), person.getIdDocumentType());
        if (existing != null){
            if (IConstants.EDIT_MODE.equals(mode) && !existing.getId().equals(person.getId())){
                throw new MAVValidationException("Ya existe una persona con el mismo tipo y número de documento");
            }
            if (IConstants.INSERT_MODE.equals(mode)) {
                throw new MAVValidationException("Ya existe una persona con el mismo tipo y número de documento");
            }
        }
    }

    //private methods

    private void setToResponse(Person person) {
        if(person.getIdCity() != null){
            person.setCity(this.cityRepository.findById(person.getIdCity()).orElseThrow(MAVValidationException::new));
        }
        if(person.getIdDocumentType() != null){
            person.setDocumentType(this.documentTypeRepository.findById(person.getIdDocumentType()).orElseThrow(MAVValidationException::new));
        }
        if(person.getIdGender() != null){
            person.setGender(this.genderRepository.findById(person.getIdGender()).orElseThrow(MAVValidationException::new));
        }
        if(person.getIdMinistry() != null){
            person.setCity(this.cityRepository.findById(person.getIdCity()).orElseThrow(MAVValidationException::new));
        }
    }

}
