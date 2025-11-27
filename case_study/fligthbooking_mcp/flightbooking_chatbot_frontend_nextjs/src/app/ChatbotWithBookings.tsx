"use client";
import { useState } from "react";
import Chatbot from "./Chatbot";
import BookingsTable, { Booking } from "./BookingsTable";

export default function ChatbotWithBookings() {
  const [bookings, setBookings] = useState<Booking[]>([]);

 return (
  <div className="flex flex-row gap-6 items-start">
    <BookingsTable bookings={bookings} />
    <Chatbot setBookings={setBookings} />
  </div>
);
}