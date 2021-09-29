package com.example.mavbackend.service.implementation;

import com.example.mavbackend.exception.MAVValidationException;
import com.example.mavbackend.model.Person;
import com.example.mavbackend.repository.IPersonRepository;
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

    @Override
    @Transactional
    public Person edit(Person person){
        validate(person, IConstants.EDIT_MODE);
        return this.personRepository.save(person);
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
}
