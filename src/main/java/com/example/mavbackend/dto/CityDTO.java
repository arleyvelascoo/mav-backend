package com.example.mavbackend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * DTO for City
 */

@Getter@Setter
public class CityDTO implements Serializable {
    private static final long serialVersionUID = -872164601886808034L;

    private static final Map<String, String> CLAVES_TO_SORT = new HashMap<>();

    static {
        CLAVES_TO_SORT.put("id", "id");
        CLAVES_TO_SORT.put("name", "name");
        CLAVES_TO_SORT.put("code", "code");
        CLAVES_TO_SORT.put("idState", "idState");
        CLAVES_TO_SORT.put("stateNamew", "state.name");
    }

    public static Map<String, String> getClavesToSort() {
        return CLAVES_TO_SORT;
    }

    private Long id;

    @NotNull@NotBlank
    private String name;

    private String code;

    private Long idState;

    private String stateNamew;
}
