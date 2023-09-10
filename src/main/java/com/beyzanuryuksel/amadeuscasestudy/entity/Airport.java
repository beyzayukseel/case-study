package com.beyzanuryuksel.amadeuscasestudy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "airport")
public class Airport {

    @Id
    private Long id;

    private String name;

    private String city;

    private String country;

    private String countryCode;

    private String iataCode;

    private String icaoCode;

    private Boolean isActive;
}
