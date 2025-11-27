"use client";
import { useEffect, useState } from "react";

export type Booking = {
  id: number;
  airline: string;
  from: string;
  to: string;
  departure: string;
  arrival: string;
  flightNumber: string;
  passenger: string;
  bookingRef: string;
};

const sampleBookings: Booking[] = [
  // Add sample bookings here if needed
];

export default function BookingsTable({ bookings: propBookings }: { bookings?: Booking[] } = {}) {
  const [bookings, setBookings] = useState<Booking[]>([]);

  useEffect(() => {
    if (propBookings && propBookings.length > 0) {
      setBookings(propBookings);
    } else {
      fetch('http://localhost:8080/api/bookings')
        .then(res => res.json())
        .then((data: Booking[]) => setBookings(data))
        .catch(() => setBookings(sampleBookings));
    }
  }, [propBookings]);

  return (
    <ol className="list-inside list-decimal text-sm/6 text-center sm:text-left font-[family-name:var(--font-geist-mono)]">
      <li className="tracking-[-.01em]">
        User Flight Bookings --:
        <div className="mt-2 overflow-x-auto">
          <table className="min-w-full border-collapse text-left text-xs sm:text-sm">
            <thead>
              <tr className="bg-gray-100 dark:bg-gray-800">
                <th className="px-2 py-1 border">Passenger</th>
                <th className="px-2 py-1 border">Booking Ref</th>
                <th className="px-2 py-1 border">Airline</th>
                <th className="px-2 py-1 border">Flight #</th>
                <th className="px-2 py-1 border">From</th>
                <th className="px-2 py-1 border">To</th>
                <th className="px-2 py-1 border">Departure</th>
                <th className="px-2 py-1 border">Arrival</th>
                <th className="px-2 py-1 border text-center">Actions</th>
              </tr>
            </thead>
            <tbody>
              {(bookings.length > 0 ? bookings : sampleBookings).map((booking) => (
                <tr key={booking.id} className="bg-white/60 dark:bg-black/30">
                  <td className="px-2 py-1 border">{booking.passenger}</td>
                  <td className="px-2 py-1 border">{booking.bookingRef}</td>
                  <td className="px-2 py-1 border">{booking.airline}</td>
                  <td className="px-2 py-1 border">{booking.flightNumber}</td>
                  <td className="px-2 py-1 border">{booking.from}</td>
                  <td className="px-2 py-1 border">{booking.to}</td>
                  <td className="px-2 py-1 border">{booking.departure}</td>
                  <td className="px-2 py-1 border">{booking.arrival}</td>
                  <td className="px-2 py-1 border text-center">
                    <button
                      title="Update"
                      className="text-blue-600 hover:underline mr-2"
                    >
                      ✏️
                    </button>
                    <button
                      title="Remove"
                      className="text-red-600 hover:underline"
                    >
                      🗑️
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="mt-2 flex justify-end">
            <button
              title="Add"
              className="text-green-600 hover:underline flex items-center gap-1"
            >
              ➕ Add Booking
            </button>
          </div>
        </div>
      </li>
    </ol>
  );
}