package com.simpleIrrigation.simpleIrrigationSystem.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    public TimeSlot getTimeSlotById(Long id) {
        return timeSlotRepository.findById(id).orElse(null);
    }

    public TimeSlot createTimeSlot(TimeSlot timeSlot) {
        System.out.println("SADASDASDA");
        System.out.println(timeSlot);
        System.out.println("SADASDASDA");
        return timeSlotRepository.save(timeSlot);
    }

    public TimeSlot updateTimeSlot(Long id, TimeSlot timeSlotDetails) {
        TimeSlot timeSlot = timeSlotRepository.findById(id).orElse(null);
        if (timeSlot != null) {
            timeSlot.setPlotOfLand(timeSlotDetails.getPlotOfLand());
            timeSlot.setFromTime(timeSlotDetails.getFromTime());
            timeSlot.setToTime(timeSlotDetails.getToTime());
            timeSlot.setAmount(timeSlotDetails.getAmount());
            return timeSlotRepository.save(timeSlot);
        }
        return null;
    }

    public void deleteTimeSlot(Long id) {
        timeSlotRepository.deleteById(id);
    }
}


