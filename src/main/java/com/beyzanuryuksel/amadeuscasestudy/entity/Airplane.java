package com.beyzanuryuksel.amadeuscasestudy.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airplane")
public class Airplane {

    @Id
    private Long id;
    private String type; //airbus 320 or boeing 737
    private String seatCapacity;
}
