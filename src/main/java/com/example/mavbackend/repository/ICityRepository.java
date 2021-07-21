package com.example.mavbackend.repository;

import com.example.mavbackend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of City
 */

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {
}
