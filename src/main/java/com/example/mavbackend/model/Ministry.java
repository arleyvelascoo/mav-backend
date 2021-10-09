package com.example.mavbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity of MINISTRIES table
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "MINISTRIES")
public class Ministry implements Serializable {
    private static final long serialVersionUID = -3672529530359726736L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ID_FIRST_LEADER")
    private Long idFirstLeader;

    @Column(name = "ID_SECOND_LEADER")
    private Long idSecondLeader;

    @Column(name = "ID_HIGHER_MINISTRY")
    private Long idHigherMinistry;

    @Column(name="ID_MINISTRY_TYPE")
    private Long idMinistryType;

    @Column(name = "ID_USER")
    private Long idUser;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FIRST_LEADER", insertable = false, updatable = false)
    private Person firstLeader;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SECOND_LEADER", insertable = false, updatable = false)
    private Person secondLeader;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HIGHER_MINISTRY", insertable = false, updatable = false)
    private Ministry higherMinistry;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MINISTRY_TYPE", insertable = false, updatable = false)
    private MinistryType ministryType;


}
