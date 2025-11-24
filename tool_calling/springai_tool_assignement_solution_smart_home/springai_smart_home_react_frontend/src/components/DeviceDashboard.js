import React, { useState } from 'react';
import { Power, Settings, Home, Shield, Lightbulb, Thermometer } from 'lucide-react';
import { apiService } from '../services/apiService';

const DeviceDashboard = ({ devices, onDeviceUpdate }) => {
    const [selectedRoom, setSelectedRoom] = useState('All');
    const [loading, setLoading] = useState(false);

    const rooms = ['All', ...new Set(devices.map(device => device.room))];

    const filteredDevices = selectedRoom === 'All'
        ? devices
        : devices.filter(device => device.room === selectedRoom);

    const getDeviceIcon = (type) => {
        switch (type) {
            case 'LIGHT': return <Lightbulb size={20} />;
            case 'THERMOSTAT': return <Thermometer size={20} />;
            case 'SECURITY': return <Shield size={20} />;
            case 'APPLIANCE': return <Home size={20} />;
            default: return <Settings size={20} />;
        }
    };

    const getDeviceColor = (type, isOn) => {
        if (!isOn) return '#6b7280';
        switch (type) {
            case 'LIGHT': return '#f59e0b';
            case 'THERMOSTAT': return '#3b82f6';
            case 'SECURITY': return '#ef4444';
            case 'APPLIANCE': return '#10b981';
            default: return '#6b7280';
        }
    };

    const handleToggleDevice = async (deviceName) => {
        setLoading(true);
        try {
            await apiService.toggleDevice(deviceName);
            if (onDeviceUpdate) {
                onDeviceUpdate();
            }
        } catch (error) {
            alert(`Error: ${error.message}`);
        } finally {
            setLoading(false);
        }
    };

    const activeDevices = devices.filter(device => device.isOn).length;
    const totalConsumption = devices
        .filter(device => device.isOn)
        .reduce((sum, device) => sum + (device.energyConsumption || 0), 0);

    return (
        <div style={{ maxWidth: '1000px', margin: '0 auto' }}>
            {/* Dashboard Stats */}
            <div style={{
                display: 'grid',
                gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))',
                gap: '20px',
                marginBottom: '30px'
            }}>
                <div className="card" style={{ padding: '20px', textAlign: 'center' }}>
                    <h3 style={{ color: '#10b981', fontSize: '24px', margin: '0 0 8px 0' }}>
                        {activeDevices}
                    </h3>
                    <p style={{ color: '#6b7280', margin: 0 }}>Active Devices</p>
                </div>
                <div className="card" style={{ padding: '20px', textAlign: 'center' }}>
                    <h3 style={{ color: '#3b82f6', fontSize: '24px', margin: '0 0 8px 0' }}>
                        {devices.length}
                    </h3>
                    <p style={{ color: '#6b7280', margin: 0 }}>Total Devices</p>
                </div>
                <div className="card" style={{ padding: '20px', textAlign: 'center' }}>
                    <h3 style={{ color: '#f59e0b', fontSize: '24px', margin: '0 0 8px 0' }}>
                        {totalConsumption.toFixed(0)}W
                    </h3>
                    <p style={{ color: '#6b7280', margin: 0 }}>Power Usage</p>
                </div>
                <div className="card" style={{ padding: '20px', textAlign: 'center' }}>
                    <h3 style={{ color: '#8b5cf6', fontSize: '24px', margin: '0 0 8px 0' }}>
                        {rooms.length - 1}
                    </h3>
                    <p style={{ color: '#6b7280', margin: 0 }}>Rooms</p>
                </div>
            </div>

            {/* Room Filter */}
            <div className="card" style={{ padding: '20px', marginBottom: '20px' }}>
                <h3 style={{ margin: '0 0 16px 0', color: '#1f2937' }}>Filter by Room</h3>
                <div style={{ display: 'flex', gap: '12px', flexWrap: 'wrap' }}>
                    {rooms.map(room => (
                        <button
                            key={room}
                            onClick={() => setSelectedRoom(room)}
                            className="btn"
                            style={{
                                background: selectedRoom === room ? '#3b82f6' : '#f3f4f6',
                                color: selectedRoom === room ? 'white' : '#374151'
                            }}
                        >
                            {room}
                        </button>
                    ))}
                </div>
            </div>

            {/* Devices Grid */}
            <div style={{
                display: 'grid',
                gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))',
                gap: '20px'
            }}>
                {filteredDevices.map(device => (
                    <div key={device.id} className="card" style={{ padding: '20px' }}>
                        <div style={{
                            display: 'flex',
                            justifyContent: 'space-between',
                            alignItems: 'flex-start',
                            marginBottom: '16px'
                        }}>
                            <div style={{ display: 'flex', alignItems: 'center', gap: '12px' }}>
                                <div style={{
                                    color: getDeviceColor(device.type, device.isOn),
                                    display: 'flex',
                                    alignItems: 'center'
                                }}>
                                    {getDeviceIcon(device.type)}
                                </div>
                                <div>
                                    <h4 style={{ margin: '0 0 4px 0', color: '#1f2937' }}>
                                        {device.name}
                                    </h4>
                                    <p style={{
                                        margin: 0,
                                        fontSize: '12px',
                                        color: '#6b7280',
                                        background: '#f3f4f6',
                                        padding: '2px 8px',
                                        borderRadius: '12px',
                                        display: 'inline-block'
                                    }}>
                                        {device.room}
                                    </p>
                                </div>
                            </div>

                            <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                                <span className={`status-indicator ${device.isOn ? 'status-on' : 'status-off'}`}></span>
                                <span style={{
                                    fontSize: '12px',
                                    color: device.isOn ? '#10b981' : '#ef4444',
                                    fontWeight: '500'
                                }}>
                  {device.isOn ? 'ON' : 'OFF'}
                </span>
                            </div>
                        </div>

                        <div style={{ marginBottom: '16px' }}>
                            {device.value && (
                                <p style={{ margin: '0 0 8px 0', fontSize: '14px', color: '#374151' }}>
                                    <strong>Value:</strong> {device.value}
                                    {device.type === 'LIGHT' && '%'}
                                    {device.type === 'THERMOSTAT' && '°C'}
                                </p>
                            )}
                            {device.status && (
                                <p style={{ margin: '0 0 8px 0', fontSize: '14px', color: '#374151' }}>
                                    <strong>Status:</strong> {device.status}
                                </p>
                            )}
                            <p style={{ margin: '0', fontSize: '14px', color: '#6b7280' }}>
                                <strong>Energy:</strong> {device.energyConsumption || 0}W
                            </p>
                        </div>

                        <button
                            onClick={() => handleToggleDevice(device.name)}
                            disabled={loading}
                            className="btn"
                            style={{
                                width: '100%',
                                background: device.isOn ? '#ef4444' : '#10b981',
                                color: 'white',
                                opacity: loading ? 0.6 : 1
                            }}
                        >
                            <Power size={16} />
                            {loading ? 'Updating...' : (device.isOn ? 'Turn Off' : 'Turn On')}
                        </button>
                    </div>
                ))}
            </div>

            {filteredDevices.length === 0 && (
                <div className="card" style={{
                    padding: '40px',
                    textAlign: 'center',
                    color: '#6b7280'
                }}>
                    <p>No devices found in {selectedRoom === 'All' ? 'any room' : selectedRoom}.</p>
                </div>
            )}
        </div>
    );
};

export default DeviceDashboard;