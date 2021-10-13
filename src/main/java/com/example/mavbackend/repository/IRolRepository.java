package com.example.mavbackend.repository;

import com.example.mavbackend.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol,Long> {

    Optional<Rol> findTopByNameIgnoreCase(String name);
}
