package com.example.mavbackend.repository;

import com.example.mavbackend.model.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository de prueba
 */

@Repository
public interface IPruebaRepository extends JpaRepository<Prueba, Long> {
}
