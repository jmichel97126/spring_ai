import React, { useState, useEffect } from 'react';
import { apiService } from '../services/apiService';

const Header = () => {
    const [healthStatus, setHealthStatus] = useState('checking');

    useEffect(() => {
        checkBackendHealth();
    }, []);

    const checkBackendHealth = async () => {
        try {
            await apiService.checkHealth();
            setHealthStatus('healthy');
        } catch (error) {
            setHealthStatus('error');
        }
    };

    return (
        <header style={{
            background: 'rgba(255, 255, 255, 0.1)',
            backdropFilter: 'blur(10px)',
            padding: '20px',
            textAlign: 'center',
            borderBottom: '1px solid rgba(255, 255, 255, 0.2)'
        }}>
            <h1 style={{
                color: 'white',
                fontSize: '28px',
                marginBottom: '10px',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                gap: '10px'
            }}>
                🏠 Smart Home Controller
                <span style={{
                    width: '12px',
                    height: '12px',
                    borderRadius: '50%',
                    background: healthStatus === 'healthy' ? '#10b981' :
                        healthStatus === 'error' ? '#ef4444' : '#f59e0b',
                    display: 'inline-block'
                }}></span>
            </h1>
            <p style={{
                color: 'rgba(255, 255, 255, 0.8)',
                fontSize: '16px'
            }}>
                AI-Powered Home Automation with Spring AI & React
            </p>
        </header>
    );
};

export default Header;