package com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlotOfLandService {

    private final PlotOfLandRepository plotOfLandRepository;

    @Autowired
    public PlotOfLandService(PlotOfLandRepository plotOfLandRepository) {
        this.plotOfLandRepository = plotOfLandRepository;
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
}
