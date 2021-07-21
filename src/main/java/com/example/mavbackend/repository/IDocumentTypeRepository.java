package com.example.mavbackend.repository;

import com.example.mavbackend.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of DocumentType
 */

@Repository
public interface IDocumentTypeRepository extends JpaRepository<DocumentType, Long> {
}
