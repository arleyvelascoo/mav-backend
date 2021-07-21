package com.example.mavbackend.repository;

import com.example.mavbackend.model.Ministry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of Ministry
 */

@Repository
public interface IMinistryRepository extends JpaRepository<Ministry, Long> {
}
