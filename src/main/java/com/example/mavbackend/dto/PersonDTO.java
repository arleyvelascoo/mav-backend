package com.example.mavbackend.dto;

import com.example.mavbackend.util.IConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * DTO for Person
 */

@Getter@Setter
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = -6894936906478829682L;

    private static final Map<String, String> CLAVES_TO_SORT = new HashMap<>();

    static {
        CLAVES_TO_SORT.put("id", "id");
        CLAVES_TO_SORT.put("firstName", "firstName");
        CLAVES_TO_SORT.put("lastName", "lastName");
        CLAVES_TO_SORT.put("documentNumber", "documentNumber");
        CLAVES_TO_SORT.put("address", "address");
        CLAVES_TO_SORT.put("neighborhood", "neighborhood");
        CLAVES_TO_SORT.put("email", "email");
        CLAVES_TO_SORT.put("birthDate", "birthDate");
        CLAVES_TO_SORT.put("hasEncounter", "hasEncounter");
        CLAVES_TO_SORT.put("isLeader", "isLeader");
        CLAVES_TO_SORT.put("wasBaptized", "wasBaptized");
        CLAVES_TO_SORT.put("idDocumentType", "idDocumentType");
        CLAVES_TO_SORT.put("idMinistry", "idMinistry");
        CLAVES_TO_SORT.put("idCity", "idCity");
        CLAVES_TO_SORT.put("idGender", "idGender");
        CLAVES_TO_SORT.put("documentTypeName", "documentType.name");
        CLAVES_TO_SORT.put("cityName","city.name");
        CLAVES_TO_SORT.put("genderName", "gender.name");
    }

    public static Map<String, String> getClavesToSort() {
        return CLAVES_TO_SORT;
    }

    private Long id;

    private String firstName;

    private String lastName;

    private String documentNumber;

    private String address;

    private String neighborhood;

    private String phoneNumber;

    private String email;

    @JsonFormat(pattern = IConstants.PATTERN_DATE_CLIENTE, timezone = IConstants.ZONA_HORARIA_COLOMBIA)
    private Date birthDate;

    private Boolean hasEncounter;

    private Boolean isLeader;

    private Boolean wasBaptized;

    private Long idDocumentType;

    private Long idMinistry;

    private Long idCity;

    private Long idGender;

    private String documentTypeName;

    private String cityName;

    private String genderName;
}
