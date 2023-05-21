package com.simpleIrrigation.simpleIrrigationSystem.TimeSlot;

import com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand.PlotOfLand;
import com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand.PlotOfLandRepository;
import com.simpleIrrigation.simpleIrrigationSystem.TimeSlot.DTO.TimeSlotRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final PlotOfLandRepository plotOfLandRepository;

    @Autowired
    public TimeSlotService(TimeSlotRepository timeSlotRepository, PlotOfLandRepository plotOfLandRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.plotOfLandRepository = plotOfLandRepository;
    }

    public List<TimeSlot> getAllTimeSlots() {
        return this.timeSlotRepository.findAll();
    }

    public TimeSlot getTimeSlotById(Long id) {
        return this.timeSlotRepository.findById(id).orElse(null);
    }

    public TimeSlot createTimeSlot(TimeSlotRequestDTO timeSlotRequestDTO) {
        Optional<PlotOfLand> optionalPlotOfLand = this.plotOfLandRepository.findById(timeSlotRequestDTO.getPlotOfLandId());

        if (optionalPlotOfLand.isPresent()) {
            PlotOfLand plotOfLand = optionalPlotOfLand.get();

            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setFromTime(timeSlotRequestDTO.getFromTime());
            timeSlot.setToTime(timeSlotRequestDTO.getToTime());
            timeSlot.setAmount(timeSlotRequestDTO.getAmount());
            timeSlot.setPlotOfLand(plotOfLand);

            return timeSlotRepository.save(timeSlot);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PlotOfLand not found");
        }
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

    public List<TimeSlot> findByFromTime(Date startTime){
        return  this.timeSlotRepository.findByFromTime(startTime);
    }
}


