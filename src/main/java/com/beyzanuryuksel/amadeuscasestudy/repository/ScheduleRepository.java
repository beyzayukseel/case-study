package com.beyzanuryuksel.amadeuscasestudy.repository;

import com.beyzanuryuksel.amadeuscasestudy.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
