package com.simpleIrrigation.simpleIrrigationSystem.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("SELECT d FROM Device d WHERE d.plotOfLand.id < :plotOfLandId")
    Device findByPlotOfLandId(@Param("plotOfLandId") Long plotOfLandId);

}

