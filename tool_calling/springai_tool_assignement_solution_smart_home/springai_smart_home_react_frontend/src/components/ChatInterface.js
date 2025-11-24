import React, { useState, useRef, useEffect } from 'react';
import { Send, MessageCircle, Loader } from 'lucide-react';
import { apiService } from '../services/apiService';

const ChatInterface = ({ onDeviceUpdate }) => {
    const [messages, setMessages] = useState([
        {
            id: 1,
            type: 'assistant',
            content: 'Hello! I\'m your Smart Home AI assistant. You can ask me to control lights, temperature, security devices, or check energy consumption. Try saying "Turn on the living room light" or "Set the thermostat to 22 degrees".',
            timestamp: new Date()
        }
    ]);
    const [inputMessage, setInputMessage] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const messagesEndRef = useRef(null);

    const scrollToBottom = () => {
        messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
    };

    useEffect(() => {
        scrollToBottom();
    }, [messages]);

    const sendMessage = async () => {
        if (!inputMessage.trim() || isLoading) return;

        const userMessage = {
            id: Date.now(),
            type: 'user',
            content: inputMessage,
            timestamp: new Date()
        };

        setMessages(prev => [...prev, userMessage]);
        setInputMessage('');
        setIsLoading(true);

        try {
            const response = await apiService.sendChatMessage(inputMessage);

            const assistantMessage = {
                id: Date.now() + 1,
                type: 'assistant',
                content: response.response,
                timestamp: new Date()
            };

            setMessages(prev => [...prev, assistantMessage]);

            // Refresh devices after AI interaction
            if (onDeviceUpdate) {
                setTimeout(onDeviceUpdate, 500);
            }
        } catch (error) {
            const errorMessage = {
                id: Date.now() + 1,
                type: 'error',
                content: `Sorry, I encountered an error: ${error.message}`,
                timestamp: new Date()
            };
            setMessages(prev => [...prev, errorMessage]);
        } finally {
            setIsLoading(false);
        }
    };

    const handleKeyPress = (e) => {
        if (e.key === 'Enter' && !e.shiftKey) {
            e.preventDefault();
            sendMessage();
        }
    };

    const quickCommands = [
        "Turn on living room light",
        "Set thermostat to 22 degrees",
        "Show energy consumption",
        "Lock front door",
        "Turn off all lights in kitchen"
    ];

    const handleQuickCommand = (command) => {
        setInputMessage(command);
    };

    return (
        <div className="card" style={{
            maxWidth: '800px',
            margin: '0 auto',
            height: '600px',
            display: 'flex',
            flexDirection: 'column'
        }}>
            <div style={{
                padding: '20px',
                borderBottom: '1px solid #e5e7eb',
                background: '#f8f9fa',
                borderRadius: '12px 12px 0 0'
            }}>
                <h2 style={{
                    margin: '0',
                    display: 'flex',
                    alignItems: 'center',
                    gap: '10px',
                    color: '#1f2937'
                }}>
                    <MessageCircle size={24} />
                    AI Chat Assistant
                </h2>
            </div>

            <div style={{
                flex: 1,
                padding: '20px',
                overflowY: 'auto',
                background: '#fff'
            }}>
                {messages.map((message) => (
                    <div
                        key={message.id}
                        style={{
                            marginBottom: '16px',
                            display: 'flex',
                            justifyContent: message.type === 'user' ? 'flex-end' : 'flex-start'
                        }}
                    >
                        <div
                            style={{
                                maxWidth: '80%',
                                padding: '12px 16px',
                                borderRadius: message.type === 'user' ? '18px 18px 4px 18px' : '18px 18px 18px 4px',
                                background: message.type === 'user' ? '#3b82f6' :
                                    message.type === 'error' ? '#fef2f2' : '#f3f4f6',
                                color: message.type === 'user' ? 'white' :
                                    message.type === 'error' ? '#dc2626' : '#1f2937',
                                border: message.type === 'error' ? '1px solid #fecaca' : 'none'
                            }}
                        >
                            <p style={{ margin: 0, lineHeight: '1.5' }}>
                                {message.content}
                            </p>
                            <small style={{
                                opacity: 0.7,
                                fontSize: '12px',
                                display: 'block',
                                marginTop: '4px'
                            }}>
                                {message.timestamp.toLocaleTimeString()}
                            </small>
                        </div>
                    </div>
                ))}

                {isLoading && (
                    <div style={{ display: 'flex', justifyContent: 'flex-start', marginBottom: '16px' }}>
                        <div style={{
                            padding: '12px 16px',
                            borderRadius: '18px 18px 18px 4px',
                            background: '#f3f4f6',
                            display: 'flex',
                            alignItems: 'center',
                            gap: '8px'
                        }}>
                            <Loader size={16} className="animate-spin" />
                            <span>AI is thinking...</span>
                        </div>
                    </div>
                )}
                <div ref={messagesEndRef} />
            </div>

            <div style={{
                padding: '20px',
                borderTop: '1px solid #e5e7eb',
                background: '#f8f9fa'
            }}>
                <div style={{ marginBottom: '12px' }}>
                    <p style={{
                        fontSize: '12px',
                        color: '#6b7280',
                        marginBottom: '8px'
                    }}>
                        Quick commands:
                    </p>
                    <div style={{
                        display: 'flex',
                        gap: '8px',
                        flexWrap: 'wrap'
                    }}>
                        {quickCommands.map((command, index) => (
                            <button
                                key={index}
                                onClick={() => handleQuickCommand(command)}
                                style={{
                                    padding: '4px 8px',
                                    fontSize: '11px',
                                    background: '#e5e7eb',
                                    border: 'none',
                                    borderRadius: '12px',
                                    cursor: 'pointer',
                                    color: '#374151',
                                    transition: 'background-color 0.2s'
                                }}
                                onMouseOver={(e) => e.target.style.background = '#d1d5db'}
                                onMouseOut={(e) => e.target.style.background = '#e5e7eb'}
                            >
                                {command}
                            </button>
                        ))}
                    </div>
                </div>

                <div style={{ display: 'flex', gap: '12px' }}>
          <textarea
              value={inputMessage}
              onChange={(e) => setInputMessage(e.target.value)}
              onKeyPress={handleKeyPress}
              placeholder="Ask me to control your smart home devices..."
              className="input"
              style={{
                  flex: 1,
                  minHeight: '44px',
                  maxHeight: '100px',
                  resize: 'vertical',
                  fontFamily: 'inherit'
              }}
              disabled={isLoading}
          />
                    <button
                        onClick={sendMessage}
                        disabled={!inputMessage.trim() || isLoading}
                        className="btn btn-primary"
                        style={{
                            height: '44px',
                            opacity: (!inputMessage.trim() || isLoading) ? 0.5 : 1
                        }}
                    >
                        <Send size={16} />
                    </button>
                </div>
            </div>
        </div>
    );
};

export default ChatInterface;