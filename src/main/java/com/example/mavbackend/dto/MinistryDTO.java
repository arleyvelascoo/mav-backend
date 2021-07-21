package com.example.mavbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * DTO for Ministry
 */

@Getter@Setter
public class MinistryDTO implements Serializable {
    private static final long serialVersionUID = 5958299351285650914L;

    private static final Map<String, String> CLAVES_TO_SORT = new HashMap<>();

    static {
        CLAVES_TO_SORT.put("id", "id");
        CLAVES_TO_SORT.put("idFirstLeader", "idFirstLeader");
        CLAVES_TO_SORT.put("idSecondLeader", "idSecondLeader");
        CLAVES_TO_SORT.put("idHigherMinistry", "idHigherMinistry");
        CLAVES_TO_SORT.put("firstLeaderName", "firstLeader.lastNameAndFirstName");
        CLAVES_TO_SORT.put("secondLeaderName", "secondLeader.lastNameAndFirstName");
    }

    public static Map<String, String> getClavesToSort() {
        return CLAVES_TO_SORT;
    }

    private Long id;

    private Long idFirstLeader;

    private Long idSecondLeader;

    private Long idHigherMinistry;

    private String firstLeaderName;

    private String secondLeaderName;
}
