package com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plot-of-lands")
public class PlotOfLandController {

    private final PlotOfLandService plotOfLandService;

    @Autowired
    public PlotOfLandController(PlotOfLandService plotOfLandService, PlotOfLandService plotOfLandService1) {
        this.plotOfLandService = plotOfLandService1;
    }

    @GetMapping
    public List<PlotOfLand> getAllPlotOfLands() {
        return this.plotOfLandService.getAllPlotOfLands();
    }

    @GetMapping("/{id}")
    public PlotOfLand getPlotOfLandById(@PathVariable Long id) {
        return this.plotOfLandService.getPlotOfLandById(id);
    }

    @PostMapping
    public PlotOfLand createPlotOfLand(@RequestBody PlotOfLand plotOfLand) {
        return this.plotOfLandService.createPlotOfLand(plotOfLand);
    }

    @PutMapping("/{id}")
    public PlotOfLand updatePlotOfLand(@PathVariable Long id, @RequestBody PlotOfLand plotOfLandDetails) {
        return this.plotOfLandService.updatePlotOfLand(id, plotOfLandDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePlotOfLand(@PathVariable Long id) {
        this.deletePlotOfLand(id);
    }
}
