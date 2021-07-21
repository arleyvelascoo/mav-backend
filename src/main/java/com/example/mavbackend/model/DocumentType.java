package com.example.mavbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity of DOCUMENT_TYPES table
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "DOCUMENT_TYPES")
public class DocumentType implements Serializable {
    private static final long serialVersionUID = -6231888509254949047L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String acronym;
}
