# 🏠 Smart Home Controller

**Spring AI 1.0 + Spring Boot 3.5 + Next.js Ready**

## 🚀 Features

### 🤖 AI Tools (@Tool Annotations)
- **🔆 controlLights()** - Turn lights on/off, adjust brightness (0-100%)
- **🌡️ controlTemperature()** - Set/get thermostat temperature
- **🔒 controlSecurity()** - Manage cameras and locks
- **⚡ monitorEnergy()** - Track consumption and costs

### 🌐 REST API
- **💬 POST /api/chat/message** - Natural language AI chat
- **🏠 GET /api/devices** - Device management
- **🏠 GET /api/devices/room/{room}** - Room-based filtering
- **🏠 POST /api/devices/{name}/toggle** - Toggle device state

### 🗄️ Database
- **Device Entity** - Smart devices with type, room, status
- **EnergyUsage Entity** - Energy consumption tracking
- **H2 Database** - In-memory with web console
- **Sample Data** - 13 devices across 5 rooms

## 🔧 Setup

### 1. Environment
```bash
export OPENAI_API_KEY=your_openai_api_key_here
```

### 2. Run Application
```bash
mvn spring-boot:run
```

### 3. Access
- **API**: http://localhost:8080/api
- **H2 Console**: http://localhost:8080/api/h2-console
- **Health Check**: http://localhost:8080/api/chat/health

## 💬 Chat Examples

```bash
# Turn on lights
curl -X POST http://localhost:8080/api/chat/message \
  -H "Content-Type: application/json" \
  -d '{"message": "Turn on living room light to 75%", "userId": "user123"}'

# Set temperature
curl -X POST http://localhost:8080/api/chat/message \
  -H "Content-Type: application/json" \
  -d '{"message": "Set main thermostat to 24 degrees", "userId": "user123"}'

# Check energy
curl -X POST http://localhost:8080/api/chat/message \
  -H "Content-Type: application/json" \
  -d '{"message": "Show current energy consumption", "userId": "user123"}'

# Lock doors
curl -X POST http://localhost:8080/api/chat/message \
  -H "Content-Type: application/json" \
  -d '{"message": "Lock front door", "userId": "user123"}'
```

## 🏗️ Architecture

```
🌐 Frontend (Next.js)
    ↓ HTTP/REST
🔌 API Layer (Controllers)
    ↓ Tool Calls
🤖 AI Layer (Spring AI + OpenAI)
    ↓ Service Calls  
⚙️ Service & Data Layer (JPA/H2)
```

## 📱 Next.js Integration

### Frontend API Calls
```javascript
// Send chat message
const response = await fetch('http://localhost:8080/api/chat/message', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    message: "Turn on living room lights",
    userId: "user123"
  })
});

// Get all devices
const devices = await fetch('http://localhost:8080/api/devices');

// Toggle device
const toggle = await fetch('http://localhost:8080/api/devices/Living%20Room%20Light/toggle', {
  method: 'POST'
});
```

### Sample Frontend Component
```jsx
// components/ChatInterface.jsx
import { useState } from 'react';

export default function ChatInterface() {
  const [message, setMessage] = useState('');
  const [response, setResponse] = useState('');

  const sendMessage = async () => {
    const res = await fetch('http://localhost:8080/api/chat/message', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ message, userId: 'user123' })
    });
    
    const data = await res.json();
    setResponse(data.response);
  };

  return (
    <div className="p-4">
      <input 
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        placeholder="Control your smart home..."
        className="w-full p-2 border rounded"
      />
      <button onClick={sendMessage} className="mt-2 px-4 py-2 bg-blue-500 text-white rounded">
        Send
      </button>
      {response && (
        <div className="mt-4 p-3 bg-gray-100 rounded">
          {response}
        </div>
      )}
    </div>
  );
}
```

## 🧪 Testing

### Test Chat API
```bash
# Health check
curl http://localhost:8080/api/chat/health

# Natural language commands
curl -X POST http://localhost:8080/api/chat/message \
  -H "Content-Type: application/json" \
  -d '{"message": "Turn on all lights in kitchen"}'
```

### Test Device API
```bash
# Get all devices
curl http://localhost:8080/api/devices

# Get devices by room
curl http://localhost:8080/api/devices/room/Kitchen

# Toggle device
curl -X POST http://localhost:8080/api/devices/Kitchen%20Light/toggle
```

## 📊 Sample Devices

| Device | Type | Room | Status |
|--------|------|------|--------|
| Living Room Light | LIGHT | Living Room | ON (75%) |
| Main Thermostat | THERMOSTAT | Living Room | 22°C |
| Kitchen Light | LIGHT | Kitchen | ON (80%) |
| Refrigerator | APPLIANCE | Kitchen | Running |
| Front Door Camera | SECURITY | Entrance | Recording |
| Front Door Lock | SECURITY | Entrance | Locked |

## 🔧 Technology Stack

- **Spring AI 1.0** - AI tool integration
- **Spring Boot 3.5** - Backend framework
- **OpenAI GPT-4** - Language model
- **JPA/Hibernate** - Database ORM
- **H2 Database** - In-memory database
- **Maven** - Build tool
- **Java 17** - Programming language

## ⚡ Key Features

### AI-Powered Control
- **Natural Language**: "Turn on living room lights to 75%"
- **Multi-Device**: "Set all thermostats to 22 degrees"
- **Room-Based**: "Turn off all devices in bedroom"
- **Energy Monitoring**: "Show current power consumption"

### REST API Ready
- **CORS Enabled** for Next.js frontend
- **JSON Responses** with success/error handling
- **Room Filtering** and device management
- **Real-time Updates** via API calls

### Production Ready
- **Error Handling** with meaningful messages
- **Validation** for device operations and parameters
- **Sample Data** for immediate testing
- **Web Console** for database inspection

## 🚀 Next Steps

1. **Frontend Development**: Build Next.js chat interface
2. **Authentication**: Add user login and sessions
3. **Real Hardware**: Integrate with actual IoT devices
4. **Mobile App**: Create React Native companion
5. **Analytics**: Add usage patterns and insights

This simplified architecture provides a complete foundation for building modern AI-powered smart home systems! 🏠🤖