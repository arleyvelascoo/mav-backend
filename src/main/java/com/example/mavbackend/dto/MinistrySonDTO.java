package com.example.mavbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MinistrySonDTO implements Serializable {

    private static final long serialVersionUID = 9180182430138357929L;

    private Long id;

    private Long idFirstLeader;

    private Long idSecondLeader;

    private Long idHigherMinistry;

    private PersonDTO firstLeaderDto;

    private PersonDTO secondLeaderDto;

}
