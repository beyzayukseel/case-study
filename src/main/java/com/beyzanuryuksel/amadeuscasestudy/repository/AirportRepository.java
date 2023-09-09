package com.beyzanuryuksel.amadeuscasestudy.repository;

import com.beyzanuryuksel.amadeuscasestudy.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query
    List<Airport> findAllByCity(String cityName);
}
