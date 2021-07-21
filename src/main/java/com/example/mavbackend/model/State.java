package com.example.mavbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity of STATES table
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "STATES")
public class State implements Serializable {
    private static final long serialVersionUID = 6281670183718291678L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String code;

    private String indicative;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "ID_COUNTRY")
    private Long idCountry;

    @Column(name = "ID_CAPITAL_CITY")
    private Long idCapitalCity;

    @JoinColumn(name = "ID_COUNTRY", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @JoinColumn(name = "ID_CAPITAL_CITY", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private City capitalCity;
}
