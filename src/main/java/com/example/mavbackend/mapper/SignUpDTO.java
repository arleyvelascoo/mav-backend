package com.example.mavbackend.mapper;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class SignUpDTO implements Serializable {
    private static final long serialVersionUID = 4831082614861272L;
    @NotEmpty
    private String username;

    @NotEmpty
    @Size(max = 10)
    private char[] password;
}
