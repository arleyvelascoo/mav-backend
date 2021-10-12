package com.example.mavbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity of CITIES table
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "CITIES")
public class City implements Serializable {
    private static final long serialVersionUID = -1582551177856899715L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    @Column(name = "ID_STATE")
    private Long idState;

    @JoinColumn(name = "ID_STATE", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private State state;

    @JsonIgnore
    @Formula("(SELECT UPPER(c.name) FROM cities c ) || ' - ' || (SELECT UPPER(c.state.name) FROM cities c ) " +
            "|| ' - ' || (SELECT UPPER(c.state.country.name) FROM cities c )")
    private String cityStateCounty;

}
