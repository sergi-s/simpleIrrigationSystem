package com.simpleIrrigation.simpleIrrigationSystem.TimeSlot;

import com.simpleIrrigation.simpleIrrigationSystem.TimeSlot.DTO.TimeSlotRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-slots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @Autowired
    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @GetMapping
    public List<TimeSlot> getAllTimeSlots() {
        return this.timeSlotService.getAllTimeSlots();
    }

    @GetMapping("/{id}")
    public TimeSlot getTimeSlotById(@PathVariable Long id) {
        return this.timeSlotService.getTimeSlotById(id);
    }

    @PostMapping
    public TimeSlot createTimeSlot(@RequestBody TimeSlotRequestDTO timeSlot) {
        System.out.println("()()()()()()()()()()");
        System.out.println(timeSlot);
        System.out.println("()()()()()()()()()()");
        return this.timeSlotService.createTimeSlot(timeSlot);
    }

    @PutMapping("/{id}")
    public TimeSlot updateTimeSlot(@PathVariable Long id, @RequestBody TimeSlot timeSlotDetails) {
        return this.timeSlotService.updateTimeSlot(id, timeSlotDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTimeSlot(@PathVariable Long id) {
        this.timeSlotService.deleteTimeSlot(id);
    }
}
