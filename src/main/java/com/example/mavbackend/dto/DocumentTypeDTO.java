package com.example.mavbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DocumentTypeDTO implements Serializable {

    private static final long serialVersionUID = -6570378452756560757L;

    private Long id;

    private String documentName;

}
