import axios from 'axios';

const API_BASE_URL = process.env.NODE_ENV === 'production'
    ? 'https://your-production-api.com/api'
    : '/api';

const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const apiService = {
    // Chat API
    sendChatMessage: async (message, userId = 'react-user') => {
        try {
            const response = await api.post('/chat/message', {
                message,
                userId
            });
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.error || 'Failed to send message');
        }
    },

    // Device API
    getDevices: async () => {
        try {
            const response = await api.get('/devices');
            return response.data;
        } catch (error) {
            throw new Error('Failed to fetch devices');
        }
    },

    getDevicesByRoom: async (room) => {
        try {
            const response = await api.get(`/devices/room/${encodeURIComponent(room)}`);
            return response.data;
        } catch (error) {
            throw new Error(`Failed to fetch devices for room: ${room}`);
        }
    },

    toggleDevice: async (deviceName) => {
        try {
            const response = await api.post(`/devices/${encodeURIComponent(deviceName)}/toggle`);
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.error || `Failed to toggle ${deviceName}`);
        }
    },

    createDevice: async (device) => {
        try {
            const response = await api.post('/devices', device);
            return response.data;
        } catch (error) {
            throw new Error('Failed to create device');
        }
    },

    // Energy API
    getCurrentEnergy: async () => {
        try {
            const response = await api.get('/energy/current');
            return response.data;
        } catch (error) {
            return { currentConsumption: 0 }; // Fallback
        }
    },

    // Health Check
    checkHealth: async () => {
        try {
            const response = await api.get('/chat/health');
            return response.data;
        } catch (error) {
            throw new Error('Health check failed');
        }
    }
};