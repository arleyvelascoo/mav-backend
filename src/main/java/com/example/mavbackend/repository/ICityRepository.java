package com.example.mavbackend.repository;

import com.example.mavbackend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository of City
 */

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

    @Query("from City c where upper(concat(c.name,'-',c.state.name,'-',c.state.country.name)) like CONCAT('%',upper(:city),'%') ")
    City findByInput(String city);
}
