import React, { useState } from "react";
import SeatAvailability from "../components/SeatAvailability"; // ✅ FIXED Import
import SeatSelection from "../components/SeatSelection"; // ✅ FIXED Import

const SeatAvailabilityPage = () => {
  const [busId, setBusId] = useState(1);
  const [selectedDate, setSelectedDate] = useState("2025-02-01");
  const [availableSeats, setAvailableSeats] = useState([]);

  const handleSeatAvailability = (seats) => {
    setAvailableSeats(seats);
  };

  return (
    <div className="text-center">
      <h1 className="mb-4">🚌 Bus Booking System</h1>
      <div className="mb-3">
        <label htmlFor="busId" className="form-label">
          Select Bus:
        </label>
        <select
          id="busId"
          className="form-select"
          value={busId}
          onChange={(e) => setBusId(Number(e.target.value))}
        >
          <option value={1}>Bus 1</option>
          <option value={2}>Bus 2</option>
          <option value={3}>Bus 3</option>
        </select>
      </div>

      <div className="mb-3">
        <label htmlFor="date" className="form-label">
          Select Date:
        </label>
        <input
          type="date"
          id="date"
          className="form-control"
          value={selectedDate}
          onChange={(e) => setSelectedDate(e.target.value)}
        />
      </div>

      {/* Seat Availability Check */}
      <SeatAvailability
        busId={busId}
        selectedDate={selectedDate}
        onSeatsFetched={handleSeatAvailability}
      />

      {/* Seat Selection */}
      <SeatSelection
        busId={busId}
        selectedDate={selectedDate}
        availableSeats={availableSeats}
      />
    </div>
  );
};

export default SeatAvailabilityPage;
