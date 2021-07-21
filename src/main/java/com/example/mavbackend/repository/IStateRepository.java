package com.example.mavbackend.repository;

import com.example.mavbackend.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of State
 */

@Repository
public interface IStateRepository extends JpaRepository<State, Long> {
}
