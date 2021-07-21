package com.example.mavbackend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for Country entity
 */

@Getter
@Setter
public class CountryDTO implements Serializable {
    private static final long serialVersionUID = 2277536547213093104L;

    private Long id;

    @NotNull @NotEmpty @NotBlank
    private String name;

    @NotNull @NotEmpty @NotBlank
    private String isoName;

    private String englishName;

    private String code;

    private String code1;

    private String code2;

    private String indicative;

    @NotNull @NotEmpty @NotBlank
    private String continent;
}
