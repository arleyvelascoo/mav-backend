package com.example.mavbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO para Prueba
 */

@Getter
@Setter
@NoArgsConstructor
public class PruebaDTO implements Serializable {
    private static final long serialVersionUID = 1771747966636990426L;

    private Long id;

    private String firstName;
}
