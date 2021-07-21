package com.example.mavbackend.repository;

import com.example.mavbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of Person
 */

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
}