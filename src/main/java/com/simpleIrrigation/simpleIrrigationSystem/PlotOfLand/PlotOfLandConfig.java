package com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PlotOfLandConfig {
    @Bean
    CommandLineRunner commandLineRunner(PlotOfLandRepository repository) {
        return args -> {
            PlotOfLand pol2 = new PlotOfLand(2L, "second plot of land", 5000);


            repository.saveAll(List.of(pol2));
        };
    }

}
