package com.example.springai_smart_home_controller.service;

import com.example.springai_smart_home_controller.model.EnergyUsage;
import com.example.springai_smart_home_controller.repository.EnergyUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class EnergyService {

    @Autowired
    private EnergyUsageRepository energyUsageRepository;

    @Autowired
    private DeviceService deviceService;

    public List<EnergyUsage> getAllEnergyUsage() {
        return energyUsageRepository.findAll();
    }

    public List<EnergyUsage> getEnergyUsageByPeriod(String period) {
        return energyUsageRepository.findByPeriod(period);
    }

    public EnergyUsage recordEnergyUsage(String period, Double consumption, Double cost) {
        EnergyUsage usage = new EnergyUsage(period, consumption, cost);
        return energyUsageRepository.save(usage);
    }

    public Double getCurrentEnergyConsumption() {
        return deviceService.getTotalEnergyConsumption();
    }

    public Double getTotalCostByPeriod(String period) {
        Double total = energyUsageRepository.getTotalCostByPeriod(period);
        return total != null ? total : 0.0;
    }

    public List<EnergyUsage> getEnergyUsageInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return energyUsageRepository.findByDateRange(startDate, endDate);
    }
}