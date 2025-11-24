import React, { useState, useEffect } from 'react';
import ChatInterface from './components/ChatInterface';
import DeviceDashboard from './components/DeviceDashboard';
import EnergyMonitor from './components/EnergyMonitor';
import Header from './components/Header';
import { apiService } from './services/apiService';
import './App.css';

function App() {
  const [devices, setDevices] = useState([]);
  const [energyData, setEnergyData] = useState({ current: 0 });
  const [loading, setLoading] = useState(true);
  const [activeTab, setActiveTab] = useState('chat');

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      setLoading(true);
      const [devicesData, energyInfo] = await Promise.all([
        apiService.getDevices(),
        apiService.getCurrentEnergy()
      ]);
      setDevices(devicesData);
      setEnergyData(energyInfo);
    } catch (error) {
      console.error('Error loading data:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleDeviceUpdate = async () => {
    // Refresh devices after any update
    try {
      const devicesData = await apiService.getDevices();
      setDevices(devicesData);
      const energyInfo = await apiService.getCurrentEnergy();
      setEnergyData(energyInfo);
    } catch (error) {
      console.error('Error refreshing data:', error);
    }
  };

  if (loading) {
    return (
        <div className="app-container">
          <div className="loading">
            <div className="loading-spinner"></div>
            <p>Loading Smart Home Controller...</p>
          </div>
        </div>
    );
  }

  return (
      <div className="app-container">
        <Header />

        <nav className="tab-navigation">
          <button
              className={`tab-button ${activeTab === 'chat' ? 'active' : ''}`}
              onClick={() => setActiveTab('chat')}
          >
            💬 AI Chat
          </button>
          <button
              className={`tab-button ${activeTab === 'devices' ? 'active' : ''}`}
              onClick={() => setActiveTab('devices')}
          >
            🏠 Devices
          </button>
          <button
              className={`tab-button ${activeTab === 'energy' ? 'active' : ''}`}
              onClick={() => setActiveTab('energy')}
          >
            ⚡ Energy
          </button>
        </nav>

        <main className="main-content">
          {activeTab === 'chat' && (
              <ChatInterface onDeviceUpdate={handleDeviceUpdate} />
          )}
          {activeTab === 'devices' && (
              <DeviceDashboard
                  devices={devices}
                  onDeviceUpdate={handleDeviceUpdate}
              />
          )}
          {activeTab === 'energy' && (
              <EnergyMonitor energyData={energyData} />
          )}
        </main>
      </div>
  );
}

export default App;