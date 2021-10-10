package com.example.mavbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Table entity of PERSONS
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "PERSONS")
public class Person implements Serializable {
    private static final long serialVersionUID = -1916428034763263944L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    private String address;

    private String neighborhood;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    private String email;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "HAS_ENCOUNTER")
    private Boolean hasEncounter;

    @Column(name = "IS_LEADER")
    private Boolean isLeader;

    @Column(name = "WAS_BAPTIZED")
    private Boolean wasBaptized;

    @Column(name = "ID_DOCUMENT_TYPE")
    private Long idDocumentType;

    @Column(name = "ID_MINISTRY")
    private Long idMinistry;

    @Column(name = "ID_CITY")
    private Long idCity;

    @Column(name = "ID_GENDER")
    private Long idGender;

    @JoinColumn(name = "ID_DOCUMENT_TYPE", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private DocumentType documentType;

    @JoinColumn(name = "ID_MINISTRY", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Ministry ministry;

    @JoinColumn(name = "ID_CITY", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "ID_GENDER", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Gender gender;

    @Formula("CONCAT(LAST_NAME, ' ', FIRST_NAME)")
    private String lastNameAndFirstName;
}