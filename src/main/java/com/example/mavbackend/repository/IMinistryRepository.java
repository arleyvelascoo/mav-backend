package com.example.mavbackend.repository;

import com.example.mavbackend.model.Ministry;
import com.example.mavbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository of Ministry
 */

@Repository
public interface IMinistryRepository extends JpaRepository<Ministry, Long> {
    Ministry findTopByIdUser(Long idUser);

    @Query("from Ministry m where (m.idFirstLeader = :personId or m.secondLeader = :personId) ")
    Ministry findByIdPerson(Long personId);

    @Query("from Ministry m where (m.idFirstLeader = :personId or m.secondLeader = :personId) and m.idUser is null")
    Ministry findByIdPersonToCreate(Long personId);

    List<Ministry> findAllByIdHigherMinistry(Long idHigherMinistry);
}
