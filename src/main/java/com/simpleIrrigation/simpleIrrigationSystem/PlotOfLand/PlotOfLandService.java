package com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand;

import com.simpleIrrigation.simpleIrrigationSystem.Device.DeviceService;
import com.simpleIrrigation.simpleIrrigationSystem.TimeSlot.TimeSlot;
import com.simpleIrrigation.simpleIrrigationSystem.TimeSlot.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class PlotOfLandService {

    private final PlotOfLandRepository plotOfLandRepository;
    private final TimeSlotService timeSlotService;
    private final DeviceService deviceService;

    @Autowired
    public PlotOfLandService(PlotOfLandRepository plotOfLandRepository, TimeSlotService timeSlotService, DeviceService deviceService) {
        this.plotOfLandRepository = plotOfLandRepository;
        this.timeSlotService = timeSlotService;
        this.deviceService = deviceService;
    }

    public List<PlotOfLand> getAllPlotOfLands() {
        return plotOfLandRepository.findAll();
    }

    public PlotOfLand getPlotOfLandById(Long id) {
        return plotOfLandRepository.findById(id).orElse(null);
    }

    public PlotOfLand createPlotOfLand(PlotOfLand plotOfLand) {
        return plotOfLandRepository.save(plotOfLand);
    }

    public PlotOfLand updatePlotOfLand(Long id, PlotOfLand plotOfLandDetails) {
        PlotOfLand plotOfLand = plotOfLandRepository.findById(id).orElse(null);
        if (plotOfLand != null) {
            plotOfLand.setName(plotOfLandDetails.getName());
            plotOfLand.setArea(plotOfLandDetails.getArea());
            return plotOfLandRepository.save(plotOfLand);
        }
        return null;
    }

    public void deletePlotOfLand(Long id) {
        plotOfLandRepository.deleteById(id);
    }

    @Scheduled(fixedRate = 6000) // Runs every minute
    public void scheduleJobsBasedOnTimeSlots() {
        // get all slots that due date
        Date now = new Date();
        List<TimeSlot> timeSlots = this.timeSlotService.findByFromTime(now);

        for (TimeSlot timeSlot : timeSlots) {
            Date fromTime = timeSlot.getFromTime();

            if (now.compareTo(fromTime) >= 0) {
                // Execute the job associated with the time slot
                executeJob(timeSlot);

                timeSlot.setStatus(TimeSlot.IrrigationStatus.IRRIGATED);
                System.out.println("SHULD UPDATE STATUS"+timeSlot);
                timeSlotService.updateTimeSlot(timeSlot.getId(), timeSlot);
                System.out.println("SHULD have UPDATEd STATUS"+timeSlot);

            }
        }
    }

    private void executeJob(TimeSlot timeSlot) {
        // notify the device
        deviceService.sendRequestToDevice(timeSlot.getPlotOfLand().getId());
    }
}
