package com.example.mavbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity of GENDERS table
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "GENDERS")
public class Gender implements Serializable {
    private static final long serialVersionUID = 2797864220510184868L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
