package com.example.mavbackend.repository;

import com.example.mavbackend.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of Gender
 */

@Repository
public interface IGenderRepository extends JpaRepository<Gender, Long> {
}
