package com.example.mavbackend.repository;

import com.example.mavbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

/**
 * Repository of Person
 */

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
    Person findTopByDocumentNumberAndIdDocumentType(String documentNumber, Long idDocumentType);

    Person findByDocumentNumberAndEmail(String documentNumber,String email);

    Person findByEmail(String email);

    Person  findTopByUserId (Long id);
}
