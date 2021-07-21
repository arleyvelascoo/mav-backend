package com.example.mavbackend.service.implementation;

import com.example.mavbackend.model.Person;
import com.example.mavbackend.repository.IPersonRepository;
import com.example.mavbackend.service.interfac.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Qualifier("principalPersonService")
@AllArgsConstructor
@Service
public class PersonServiceImpl implements IPersonService {

    private final IPersonRepository personRepository;

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
    public Person save(Person person) {
        return this.personRepository.save(person);
    }
}
