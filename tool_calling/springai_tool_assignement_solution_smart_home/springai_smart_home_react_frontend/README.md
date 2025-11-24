# Smart Home Controller - React Frontend Setup

## 🚀 Quick Start

### 1. Create React App
```bash
npx create-react-app smart-home-react-frontend
cd smart-home-react-frontend
```

### 2. Install Dependencies
```bash
npm install axios lucide-react
```

### 3. Replace Files
Copy all the provided files to their respective locations:
- `src/App.js`
- `src/App.css`
- `src/components/` (create this folder)
- `public/index.html`
- `package.json` (update dependencies)

### 4. Create Component Files
Create the following files in `src/components/`:
- `Header.js`
- `ChatInterface.js`
- `DeviceDashboard.js`
- `EnergyMonitor.js`

Create `src/services/apiService.js`

### 5. Start Development Server
```bash
npm start
```

The app will open at `http://localhost:3000`

### 6. Backend Setup
Make sure your Spring Boot backend is running on `http://localhost:8080`

## 📱 Features

### 🤖 AI Chat Interface
- Real-time chat with AI assistant
- Quick command buttons
- Natural language processing
- Auto-scroll messages
- Loading states

### 🏠 Device Dashboard
- Visual device cards with status
- Room filtering
- One-click device toggle
- Energy consumption display
- Device type icons

### ⚡ Energy Monitor
- Real-time energy consumption
- Cost estimates (hourly/daily/monthly)
- Energy saving tips
- Usage patterns (placeholder)

## 🔧 Configuration

### API Proxy
The app uses a proxy configuration in `package.json`:
```json
"proxy": "http://localhost:8080"
```

### Environment Variables
For production, create `.env`:
```
REACT_APP_API_URL=https://your-production-api.com
```

## 🎨 Styling
- Pure CSS with CSS-in-JS
- Responsive design
- Card-based layout
- Color-coded components
- Smooth animations

## 📂 Project Structure
```
src/
├── components/
│   ├── Header.js
│   ├── ChatInterface.js
│   ├── DeviceDashboard.js
│   └── EnergyMonitor.js
├── services/
│   └── apiService.js
├── App.js
├── App.css
└── index.js
```

## 🔗 API Integration

### Chat API
```javascript
POST /api/chat/message
{
  "message": "Turn on living room light",
  "userId": "react-user"
}
```

### Device API
```javascript
GET /api/devices
GET /api/devices/room/{room}
POST /api/devices/{name}/toggle
```

### Energy API
```javascript
GET /api/energy/current
```

## 🚀 Deployment

### Build for Production
```bash
npm run build
```

### Deploy to Static Hosting
Upload the `build/` folder to:
- Netlify
- Vercel
- AWS S3
- GitHub Pages

Update the API URL in production environment.

## 🧪 Testing Commands

### Test Chat
Open browser console and run:
```javascript
fetch('/api/chat/message', {
  method: 'POST',
  headers: {'Content-Type': 'application/json'},
  body: JSON.stringify({message: 'Turn on living room light'})
}).then(r => r.json()).then(console.log);
```

### Test Devices
```javascript
fetch('/api/devices').then(r => r.json()).then(console.log);
```

This React frontend provides a complete, modern interface for your Smart Home Controller Spring AI backend! 🏠⚡