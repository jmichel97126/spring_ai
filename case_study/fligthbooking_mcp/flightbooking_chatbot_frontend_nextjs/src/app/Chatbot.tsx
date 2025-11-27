"use client";
import { useState } from "react";
import { Booking } from "./BookingsTable";

type ChatbotProps = {
  setBookings?: (bookings: Booking[]) => void;
};


export default function Chatbot({ setBookings }: ChatbotProps) {
  const [messages, setMessages] = useState([
    { from: "bot", text: "Hello! How can I help you with your bookings?" },
  ]);
  const [input, setInput] = useState("");
  const [loading, setLoading] = useState(false);

  const sendMessage = async () => {
    if (!input.trim() || loading) return;
    const userMessage = { from: "user", text: input };
    setMessages((prev) => [...prev, userMessage]);
    setInput("");
    setLoading(true);

    try {
      const res = await fetch("http://localhost:8080/api/chat", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: input }),
      });
      const data = await res.json();
      console.log("Response from server:", data);
      let botText = "Sorry, I didn't understand that.";
      if (data && typeof data === "object") {
        if (typeof data.message === "string") {
          botText = data.message;
        } else if (typeof data.reply === "string") {
          botText = data.reply;
        }
        // If bookings data is present, update parent state
        // console log  check if data.bookings is an array
        console.log("Bookings data:", data.bookings);
        if (Array.isArray(data.bookings)) {
          console.log("------Bookings data:is array");
          if (setBookings) {
            setBookings(data.bookings);
          }
        }else {
          console.log("------Bookings data: is not an array");
        }
        if (Array.isArray(data.bookings) && setBookings) {
          console.log("Bookings data:", data.bookings);
          setBookings(data.bookings);
        } else {
          console.error("Bookings data is not an array or setBookings is not provided");
        }
      }
      setMessages((prev) => [
        ...prev,
        { from: "bot", text: botText },
      ]);
    } catch (error) {
      setMessages((prev) => [
        ...prev,
        { from: "bot", text: "Failed to reach the chatbot server." },
      ]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="w-full sm:w-80 h-[420px] flex flex-col border rounded-lg shadow bg-white dark:bg-gray-900">
      <div className="p-3 border-b font-semibold bg-gray-50 dark:bg-gray-800">
        Flight Chatbot
      </div>
      <div className="flex-1 overflow-y-auto p-3 space-y-2 flex flex-col">
        {messages.map((msg, idx) => (
          <div
            key={idx}
            className={`text-sm px-3 py-2 rounded-lg max-w-[80%] ${msg.from === "bot"
              ? "bg-gray-200 dark:bg-gray-700 text-gray-900 dark:text-gray-100 self-start"
              : "bg-blue-500 text-white self-end"
              }`}
          >
            {msg.text}
          </div>
        ))}
        {loading && (
          <div className="text-sm px-3 py-2 rounded-lg bg-gray-200 dark:bg-gray-700 text-gray-900 dark:text-gray-100 self-start opacity-70">
            ...
          </div>
        )}
      </div>
      <form
        className="flex border-t p-2 gap-2"
        onSubmit={(e) => {
          e.preventDefault();
          sendMessage();
        }}
      >
        <input
          className="flex-1 px-2 py-1 rounded border text-sm dark:bg-gray-800 dark:text-white"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Type your message..."
          disabled={loading}
        />
        <button
          type="submit"
          className="px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700 text-sm"
          disabled={loading}
        >
          {loading ? "Sending..." : "Send"}
        </button>
      </form>
    </div>
  );
}