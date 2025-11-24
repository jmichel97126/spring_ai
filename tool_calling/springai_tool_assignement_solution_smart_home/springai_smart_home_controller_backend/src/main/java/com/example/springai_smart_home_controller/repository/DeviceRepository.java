package com.example.springai_smart_home_controller.repository;


import com.example.springai_smart_home_controller.model.Device;
import com.example.springai_smart_home_controller.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByRoom(String room);
    List<Device> findByType(DeviceType type);
    Optional<Device> findByNameIgnoreCase(String name);
    
    @Query("SELECT SUM(d.energyConsumption) FROM Device d WHERE d.isOn = true")
    Double getTotalEnergyConsumption();
}