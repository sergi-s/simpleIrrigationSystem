package com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand;

import static org.junit.jupiter.api.Assertions.*;

import com.simpleIrrigation.simpleIrrigationSystem.Device.Device;
import com.simpleIrrigation.simpleIrrigationSystem.Device.DeviceService;
import com.simpleIrrigation.simpleIrrigationSystem.TimeSlot.TimeSlot;
import com.simpleIrrigation.simpleIrrigationSystem.TimeSlot.TimeSlotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlotOfLandServiceTest {


    @Mock
    private PlotOfLandRepository plotOfLandRepository;

    @Mock
    private TimeSlotService timeSlotService;

    @Mock
    private DeviceService deviceService;

    private PlotOfLandService plotOfLandService;

    @BeforeEach
    public void setUp() {
        plotOfLandService = new PlotOfLandService(plotOfLandRepository, timeSlotService, deviceService);
    }

    @Test
    public void testGetAllPlotOfLands() {
        List<PlotOfLand> expectedPlotOfLands = Arrays.asList(new PlotOfLand(), new PlotOfLand());

        when(plotOfLandRepository.findAll()).thenReturn(expectedPlotOfLands);

        List<PlotOfLand> actualPlotOfLands = plotOfLandService.getAllPlotOfLands();

        assertEquals(expectedPlotOfLands, actualPlotOfLands);
    }

    @Test
    public void testGetPlotOfLandById() {
        PlotOfLand expectedPlotOfLand = new PlotOfLand();

        when(plotOfLandRepository.findById(1L)).thenReturn(Optional.of(expectedPlotOfLand));

        PlotOfLand actualPlotOfLand = plotOfLandService.getPlotOfLandById(1L);

        assertEquals(expectedPlotOfLand, actualPlotOfLand);
    }

    @Test
    public void testCreatePlotOfLand() {
        PlotOfLand plotOfLand = new PlotOfLand();

        when(plotOfLandRepository.save(plotOfLand)).thenReturn(plotOfLand);

        PlotOfLand actualPlotOfLand = plotOfLandService.createPlotOfLand(plotOfLand);

        assertEquals(plotOfLand, actualPlotOfLand);
    }

    @Test
    public void testUpdatePlotOfLand() {
        PlotOfLand expectedPlotOfLand = new PlotOfLand();

        PlotOfLandRepository mockPlotOfLandRepository = Mockito.mock(PlotOfLandRepository.class);

        when(mockPlotOfLandRepository.findById(1L)).thenReturn(Optional.of(expectedPlotOfLand));

        PlotOfLand actualPlotOfLand = plotOfLandService.updatePlotOfLand(1L, new PlotOfLand());

        assertEquals(expectedPlotOfLand, actualPlotOfLand);
    }

    @Test
    public void testDeletePlotOfLand() {
        plotOfLandService.deletePlotOfLand(1L);

        verify(plotOfLandRepository).deleteById(1L);
    }

    @Test
    public void testScheduleJobsBasedOnTimeSlots() {
        List<TimeSlot> timeSlots = Arrays.asList(new TimeSlot(), new TimeSlot());

        when(timeSlotService.findByFromTime(new Date())).thenReturn(timeSlots);

        plotOfLandService.scheduleJobsBasedOnTimeSlots();

        for (TimeSlot timeSlot : timeSlots) {
            verify(deviceService).sendRequestToDevice(timeSlot.getPlotOfLand().getDevice());
        }
    }

    @Test
    public void testExecuteJob() {
        Device device = new Device();
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setPlotOfLand(new PlotOfLand());
        timeSlot.getPlotOfLand().setDevice(device);

        plotOfLandService.executeJob(timeSlot);

        // Verify that the sendRequestToDevice() method was called
        verify(deviceService).sendRequestToDevice(device);
    }
}