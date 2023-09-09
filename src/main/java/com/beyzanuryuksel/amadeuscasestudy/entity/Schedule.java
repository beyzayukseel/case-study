package com.beyzanuryuksel.amadeuscasestudy.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
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

    private Boolean isActive;

}
