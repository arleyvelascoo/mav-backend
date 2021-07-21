package com.example.mavbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Table entity of COUNTRIES
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "COUNTRIES")
public class Country implements Serializable {
    private static final long serialVersionUID = 5253510996123412263L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "ISO_NAME")
    private String isoName;

    @Column(name = "ENGLISH_NAME")
    private String englishName;

    private String code;

    @Column(name = "CODE_1")
    private String code1;

    @Column(name = "CODE_2")
    private String code2;

    private String indicative;

    private String continent;

    @Column(name = "ID_CAPITAL_CITY")
    private Long idCapitalCity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CAPITAL_CITY", insertable = false, updatable = false)
    private City capitalCity;
}
