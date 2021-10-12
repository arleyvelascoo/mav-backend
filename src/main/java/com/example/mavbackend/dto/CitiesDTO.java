package com.example.mavbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CitiesDTO implements Serializable {

    private static final long serialVersionUID = 628382950826327003L;

    private Long id;

    private String cityStateCounty;

}
