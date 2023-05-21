package com.simpleIrrigation.simpleIrrigationSystem.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/time-slots")
public class TimeSlotController {

    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public TimeSlotController(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    @GetMapping
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    @GetMapping("/{id}")
    public TimeSlot getTimeSlotById(@PathVariable Long id) {
        return timeSlotRepository.findById(id).orElse(null);
    }

    @PostMapping
    public TimeSlot createTimeSlot(@RequestBody TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }

    @PutMapping("/{id}")
    public TimeSlot updateTimeSlot(@PathVariable Long id, @RequestBody TimeSlot timeSlotDetails) {
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

    @DeleteMapping("/{id}")
    public void deleteTimeSlot(@PathVariable Long id) {
        timeSlotRepository.deleteById(id);
    }
}
