package com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand;

import com.simpleIrrigation.simpleIrrigationSystem.Device.Device;
import com.simpleIrrigation.simpleIrrigationSystem.Device.DeviceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PlotOfLandConfig {
    @Bean
    CommandLineRunner commandLineRunner(PlotOfLandRepository plotOfLandRepository, DeviceRepository deviceRepository) {
        return args -> {
            PlotOfLand secondPlotOfLand = new PlotOfLand();
            secondPlotOfLand.setName("second plot of land");
            secondPlotOfLand.setArea(5000.0);

            Device device1 = new Device();
            device1.setId(1L);
            device1.setPlotOfLand(secondPlotOfLand);

            plotOfLandRepository.saveAll(List.of(secondPlotOfLand));
            deviceRepository.save(device1);
        };
    }



}
