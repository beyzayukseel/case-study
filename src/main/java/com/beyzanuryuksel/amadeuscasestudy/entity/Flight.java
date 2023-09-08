package com.beyzanuryuksel.amadeuscasestudy.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    private Long id;
    private String flightNumber;

    @ManyToOne
    private Airplane airplane;

    @ManyToOne
    private Schedule schedule;

    private String currency;

    private Double amount;

}
