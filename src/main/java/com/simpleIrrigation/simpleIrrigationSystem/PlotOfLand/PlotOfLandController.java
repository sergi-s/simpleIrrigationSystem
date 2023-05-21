package com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/plot-of-lands")
public class PlotOfLandController {

    private final PlotOfLandRepository plotOfLandRepository;

    @Autowired
    public PlotOfLandController(PlotOfLandRepository plotOfLandRepository) {
        this.plotOfLandRepository = plotOfLandRepository;
    }

    @GetMapping
    public List<PlotOfLand> getAllPlotOfLands() {
        return plotOfLandRepository.findAll();
    }

    @GetMapping("/{id}")
    public PlotOfLand getPlotOfLandById(@PathVariable Long id) {
        return plotOfLandRepository.findById(id).orElse(null);
    }

    @PostMapping
    public PlotOfLand createPlotOfLand(@RequestBody PlotOfLand plotOfLand) {
        return plotOfLandRepository.save(plotOfLand);
    }

    @PutMapping("/{id}")
    public PlotOfLand updatePlotOfLand(@PathVariable Long id, @RequestBody PlotOfLand plotOfLandDetails) {
        PlotOfLand plotOfLand = plotOfLandRepository.findById(id).orElse(null);
        if (plotOfLand != null) {
            plotOfLand.setName(plotOfLandDetails.getName());
            plotOfLand.setArea(plotOfLandDetails.getArea());
            return plotOfLandRepository.save(plotOfLand);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePlotOfLand(@PathVariable Long id) {
        plotOfLandRepository.deleteById(id);
    }
}
