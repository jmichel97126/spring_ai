package com.example.springai_smart_home_controller.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ENERGY_USAGE")
public class EnergyUsage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String period;
    
    @Column(nullable = false)
    private Double consumption;
    
    @Column(nullable = false)
    private Double cost;
    
    @Column(nullable = false)
    private LocalDateTime recordedAt;
    
    public EnergyUsage() {
        this.recordedAt = LocalDateTime.now();
    }
    
    public EnergyUsage(String period, Double consumption, Double cost) {
        this();
        this.period = period;
        this.consumption = consumption;
        this.cost = cost;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
    
    public Double getConsumption() { return consumption; }
    public void setConsumption(Double consumption) { this.consumption = consumption; }
    
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    
    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
}