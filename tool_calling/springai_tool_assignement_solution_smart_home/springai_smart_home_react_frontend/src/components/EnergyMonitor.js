import React, { useState, useEffect } from 'react';
import { Zap, TrendingUp, DollarSign, Clock } from 'lucide-react';
import { apiService } from '../services/apiService';

const EnergyMonitor = ({ energyData }) => {
    const [currentConsumption, setCurrentConsumption] = useState(0);
    const [refreshing, setRefreshing] = useState(false);

    useEffect(() => {
        if (energyData?.currentConsumption) {
            setCurrentConsumption(energyData.currentConsumption);
        }
    }, [energyData]);

    const refreshEnergyData = async () => {
        setRefreshing(true);
        try {
            const data = await apiService.getCurrentEnergy();
            setCurrentConsumption(data.currentConsumption || 0);
        } catch (error) {
            console.error('Error refreshing energy data:', error);
        } finally {
            setRefreshing(false);
        }
    };

    // Calculate estimated costs (example rates)
    const estimatedHourlyCost = (currentConsumption * 0.12) / 1000; // $0.12 per kWh
    const estimatedDailyCost = estimatedHourlyCost * 24;
    const estimatedMonthlyCost = estimatedDailyCost * 30;

    // Energy efficiency tips
    const tips = [
        "Turn off lights when leaving a room",
        "Use LED bulbs for better efficiency",
        "Set thermostat 2°C lower in winter",
        "Unplug devices when not in use",
        "Use energy-saving modes on appliances"
    ];

    return (
        <div style={{ maxWidth: '1000px', margin: '0 auto' }}>
            {/* Current Consumption Card */}
            <div className="card" style={{
                padding: '30px',
                marginBottom: '30px',
                background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
                color: 'white'
            }}>
                <div style={{
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    marginBottom: '20px'
                }}>
                    <h2 style={{
                        margin: 0,
                        display: 'flex',
                        alignItems: 'center',
                        gap: '12px'
                    }}>
                        <Zap size={28} />
                        Current Energy Usage
                    </h2>
                    <button
                        onClick={refreshEnergyData}
                        disabled={refreshing}
                        className="btn"
                        style={{
                            background: 'rgba(255, 255, 255, 0.2)',
                            color: 'white',
                            border: '1px solid rgba(255, 255, 255, 0.3)'
                        }}
                    >
                        {refreshing ? 'Refreshing...' : 'Refresh'}
                    </button>
                </div>

                <div style={{ textAlign: 'center' }}>
                    <h1 style={{
                        fontSize: '48px',
                        margin: '0 0 10px 0',
                        fontWeight: 'bold'
                    }}>
                        {currentConsumption.toFixed(1)}
                    </h1>
                    <p style={{
                        fontSize: '18px',
                        margin: 0,
                        opacity: 0.9
                    }}>
                        Watts Currently Used
                    </p>
                </div>
            </div>

            {/* Cost Estimates */}
            <div style={{
                display: 'grid',
                gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))',
                gap: '20px',
                marginBottom: '30px'
            }}>
                <div className="card" style={{ padding: '20px', textAlign: 'center' }}>
                    <div style={{
                        display: 'flex',
                        justifyContent: 'center',
                        marginBottom: '12px',
                        color: '#3b82f6'
                    }}>
                        <Clock size={24} />
                    </div>
                    <h3 style={{ color: '#3b82f6', fontSize: '20px', margin: '0 0 8px 0' }}>
                        ${estimatedHourlyCost.toFixed(3)}
                    </h3>
                    <p style={{ color: '#6b7280', margin: 0 }}>Estimated Hourly Cost</p>
                </div>

                <div className="card" style={{ padding: '20px', textAlign: 'center' }}>
                    <div style={{
                        display: 'flex',
                        justifyContent: 'center',
                        marginBottom: '12px',
                        color: '#10b981'
                    }}>
                        <TrendingUp size={24} />
                    </div>
                    <h3 style={{ color: '#10b981', fontSize: '20px', margin: '0 0 8px 0' }}>
                        ${estimatedDailyCost.toFixed(2)}
                    </h3>
                    <p style={{ color: '#6b7280', margin: 0 }}>Estimated Daily Cost</p>
                </div>

                <div className="card" style={{ padding: '20px', textAlign: 'center' }}>
                    <div style={{
                        display: 'flex',
                        justifyContent: 'center',
                        marginBottom: '12px',
                        color: '#f59e0b'
                    }}>
                        <DollarSign size={24} />
                    </div>
                    <h3 style={{ color: '#f59e0b', fontSize: '20px', margin: '0 0 8px 0' }}>
                        ${estimatedMonthlyCost.toFixed(0)}
                    </h3>
                    <p style={{ color: '#6b7280', margin: 0 }}>Estimated Monthly Cost</p>
                </div>
            </div>

            {/* Energy Efficiency Tips */}
            <div className="card" style={{ padding: '30px' }}>
                <h3 style={{
                    margin: '0 0 20px 0',
                    color: '#1f2937',
                    display: 'flex',
                    alignItems: 'center',
                    gap: '12px'
                }}>
                    💡 Energy Saving Tips
                </h3>
                <div style={{
                    display: 'grid',
                    gridTemplateColumns: 'repeat(auto-fit, minmax(280px, 1fr))',
                    gap: '16px'
                }}>
                    {tips.map((tip, index) => (
                        <div key={index} style={{
                            padding: '16px',
                            background: '#f8f9fa',
                            borderRadius: '8px',
                            borderLeft: '4px solid #10b981'
                        }}>
                            <p style={{ margin: 0, color: '#374151' }}>
                                {tip}
                            </p>
                        </div>
                    ))}
                </div>
            </div>

            {/* Usage Chart Placeholder */}
            <div className="card" style={{ padding: '30px', marginTop: '30px' }}>
                <h3 style={{
                    margin: '0 0 20px 0',
                    color: '#1f2937',
                    display: 'flex',
                    alignItems: 'center',
                    gap: '12px'
                }}>
                    📊 Energy Usage Pattern
                </h3>

                <div style={{
                    height: '200px',
                    background: 'linear-gradient(135deg, #f3f4f6 0%, #e5e7eb 100%)',
                    borderRadius: '8px',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    color: '#6b7280',
                    flexDirection: 'column',
                    gap: '12px'
                }}>
                    <TrendingUp size={48} />
                    <p style={{ margin: 0, fontSize: '16px', textAlign: 'center' }}>
                        Energy usage chart will be displayed here<br />
                        <small>Connect to analytics service for detailed insights</small>
                    </p>
                </div>
            </div>
        </div>
    );
};

export default EnergyMonitor;