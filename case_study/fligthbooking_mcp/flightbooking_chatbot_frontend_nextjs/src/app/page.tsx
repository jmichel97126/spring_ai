"use client";
import BookingsTable from "./BookingsTable";
import Chatbot from "./Chatbot";
import ChatbotWithBookings from "./ChatbotWithBookings";

export default function Home() {
  return (
    <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <main className="flex flex-col sm:flex-row gap-8 row-start-2 items-start w-full max-w-6xl">
   
        {/* Chatbot Side Panel */}
        <div className="w-full sm:w-80 mt-8 sm:mt-0">
         <ChatbotWithBookings />
        </div>
      </main>
      <footer className="row-start-3 flex gap-[24px] flex-wrap items-center justify-center"></footer>
    </div>
  );
}