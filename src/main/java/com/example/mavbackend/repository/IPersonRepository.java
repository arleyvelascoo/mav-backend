package com.example.mavbackend.repository;

import com.example.mavbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository of Person
 */

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
    Person findTopByDocumentNumberAndIdDocumentType(String documentNumber, Long idDocumentType);

    Person findByDocumentNumberAndEmail(String documentNumber,String email);

    Person  findTopByUserId (Long id);

    Person findByEmailAndDocumentNumber(String email, String document);

    List<Person> findAllByIdMinistry(Long idMinistry);
}
