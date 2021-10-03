package com.example.mavbackend.repository;

import com.example.mavbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findTopByUsername(String username);
}
