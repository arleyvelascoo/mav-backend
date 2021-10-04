package com.example.mavbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USER_ROL")
public class UserRol implements Serializable {
    private static final long serialVersionUID = -8329803088123885459L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_ROL")
    private Long idRol;

    @Column(name = "ID_USER")
    private Long idUser;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "ID_ROL",insertable = false,updatable = false)
    private Rol rol;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "ID_USER",insertable = false,updatable = false)
    private User user;
}
