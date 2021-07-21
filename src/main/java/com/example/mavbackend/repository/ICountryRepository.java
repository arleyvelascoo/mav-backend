package com.example.mavbackend.repository;

import com.example.mavbackend.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of Country
 */

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {
}
