package com.example.mavbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -1888634853533408164L;
    private Long id;
    private Long idUser;
    private String username;
    private Long idRol;
    private String rolName;
    private String token;
}
