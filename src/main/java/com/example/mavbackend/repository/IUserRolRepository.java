package com.example.mavbackend.repository;

import com.example.mavbackend.model.UserRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRolRepository extends JpaRepository<UserRol, Long> {
    UserRol findTopByIdUserAndIdRol(Long idUser, Long idRol);
}
