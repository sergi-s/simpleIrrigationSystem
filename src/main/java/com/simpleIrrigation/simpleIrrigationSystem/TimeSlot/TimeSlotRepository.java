package com.simpleIrrigation.simpleIrrigationSystem.TimeSlot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    //    @Query("SELECT * FROM time_slot WHERE time_slot.from_time < :currentTime")
//    List<TimeSlot> findByFromTime(Date currentTime);
    @Query("SELECT t FROM TimeSlot t WHERE t.fromTime < :currentTime AND t.status = 1")
    List<TimeSlot> findByFromTime(@Param("currentTime") Date currentTime);
}