package com.simpleIrrigation.simpleIrrigationSystem.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByFromTime(Date startTime);
}