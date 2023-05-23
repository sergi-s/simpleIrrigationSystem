package com.simpleIrrigation.simpleIrrigationSystem.TimeSlot;

import com.simpleIrrigation.simpleIrrigationSystem.Device.Device;
import com.simpleIrrigation.simpleIrrigationSystem.Device.DeviceRepository;
import com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand.PlotOfLand;
import com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand.PlotOfLandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
public class TimeSlotConfig {
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private PlotOfLandRepository plotOfLandRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    public TimeSlotConfig(TimeSlotRepository timeSlotRepository, PlotOfLandRepository plotOfLandRepository, DeviceRepository deviceRepository) {
        this.plotOfLandRepository = plotOfLandRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.deviceRepository = deviceRepository;

    }

    @Bean
    CommandLineRunner TimeSlotCommandLineRunner() {
        return args -> {
            // Create a new Device
            Device device = new Device();
            deviceRepository.save(device);

            // Create a new PlotOfLand
            PlotOfLand plotOfLand = new PlotOfLand(1L, "plot of land name 1", 5000);
            plotOfLandRepository.save(plotOfLand);

            // Connect and save
            plotOfLand.setDevice(device);
            device.setPlotOfLand(plotOfLand);

            deviceRepository.save(device);
            plotOfLandRepository.save(plotOfLand);

            // Create a new TimeSlot and associate it with the PlotOfLand
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.HOUR_OF_DAY, 2);

            Date currentDateTime = new Date();
            Date futureDateTime = calendar.getTime();

            TimeSlot timeSlot = new TimeSlot(1L, plotOfLand, currentDateTime, futureDateTime, 50, TimeSlot.IrrigationStatus.SCHEDULED);
            timeSlotRepository.save(timeSlot);
        };
    }




}
