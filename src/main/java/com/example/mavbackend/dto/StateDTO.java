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
 * DTO for State
 */

@Getter@Setter
public class StateDTO implements Serializable {
    private static final long serialVersionUID = -4286666267865433435L;

    private static final Map<String, String> CLAVES_TO_SORT = new HashMap<>();

    static {
        CLAVES_TO_SORT.put("id", "id");
        CLAVES_TO_SORT.put("name", "name");
        CLAVES_TO_SORT.put("code", "code");
        CLAVES_TO_SORT.put("indicative", "indicative");
        CLAVES_TO_SORT.put("zipCode", "zipCode");
        CLAVES_TO_SORT.put("idCountry", "idCountry");
        CLAVES_TO_SORT.put("idCapitalCity", "idCapitalCity");
        CLAVES_TO_SORT.put("countryName", "country.name");
    }

    public static Map<String, String> getClavesToSort() {
        return CLAVES_TO_SORT;
    }

    private Long id;

    @NotNull@NotEmpty@NotBlank
    private String name;

    private String code;

    private String indicative;

    private String zipCode;

    private Long idCountry;

    private Long idCapitalCity;

    private String countryName;
}
