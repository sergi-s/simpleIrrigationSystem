package com.simpleIrrigation.simpleIrrigationSystem.TimeSlot;

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
    public TimeSlotConfig(TimeSlotRepository timeSlotRepository,PlotOfLandRepository plotOfLandRepository) {
        this.plotOfLandRepository = plotOfLandRepository;
        this.timeSlotRepository = timeSlotRepository;

    }
    @Bean
    CommandLineRunner TimeSlotCommandLineRunner() {
        return args -> {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date()); // Set the calendar's time to the current date
            calendar.add(Calendar.HOUR_OF_DAY, 2); // Add 2 hours

            // Get the new date and time
            Date currentDateTime = new Date();
            Date futureDateTime = calendar.getTime();

            PlotOfLand pol1 = new PlotOfLand(1L, "plot of land name 1", 5000);
            TimeSlot ts = new TimeSlot(1L,pol1,currentDateTime,futureDateTime,50, TimeSlot.IrrigationStatus.SCHEDULED);

            plotOfLandRepository.saveAll(List.of(pol1));

            timeSlotRepository.saveAll(List.of(ts));
        };
    }

}
