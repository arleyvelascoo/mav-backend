package com.example.mavbackend.model;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FIRST_LEADER", insertable = false, updatable = false)
    private Person firstLeader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SECOND_LEADER", insertable = false, updatable = false)
    private Person secondLeader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HIGHER_MINISTRY", insertable = false, updatable = false)
    private Ministry higherMinistry;
}
