package com.example.mavbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Prueba de base de datos
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prueba")
public class Prueba implements Serializable {
    private static final long serialVersionUID = 4652931923624832106L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;
}
