package com.beyzanuryuksel.amadeuscasestudy.entity;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    private Long id;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String utcOffset;
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport departureAirport;
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport arrivalAirport;

}
