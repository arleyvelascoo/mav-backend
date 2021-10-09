package com.example.mavbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RolDTO implements Serializable {

    private static final long serialVersionUID = 2207633213945199434L;

    private Long id;

    private String rolName;
}

