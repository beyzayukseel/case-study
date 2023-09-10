package com.beyzanuryuksel.amadeuscasestudy.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String utcOffset;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airport arrivalAirport;

    private Boolean isActive;

}
