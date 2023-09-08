package com.beyzanuryuksel.amadeuscasestudy.repository;

import com.beyzanuryuksel.amadeuscasestudy.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long>{
}
