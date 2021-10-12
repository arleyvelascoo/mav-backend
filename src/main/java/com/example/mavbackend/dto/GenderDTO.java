package com.example.mavbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class GenderDTO implements Serializable {

    private static final long serialVersionUID = -3638157178477604103L;

    private Long id;

    private String name;

}
