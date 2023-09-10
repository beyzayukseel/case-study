package com.beyzanuryuksel.amadeuscasestudy.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Setter
@Getter
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    @ManyToOne
    private Airplane airplane;

    @ManyToOne
    private Schedule schedule;

    private String currency;

    private Double amount;

    private Boolean isActive;

}
