// src/components/SeatAvailability.jsx
import React, { useEffect, useState } from "react";
import axios from "axios";

const SeatAvailability = ({ busId, selectedDate, onSeatsFetched }) => {
  const [seats, setSeats] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchSeats = async () => {
      try {
        console.log(
          `📌 Fetching seats for Bus ID: ${busId} on ${selectedDate}`
        );
        const response = await axios.get(
          `http://localhost:8080/api/buses/${busId}/seats?date=${selectedDate}`
        );
        setSeats(response.data);
        onSeatsFetched(response.data);
      } catch (err) {
        console.error("❌ Error fetching seats:", err);
        setError("Error loading seats. Please try again.");
      }
    };

    fetchSeats();
  }, [busId, selectedDate, onSeatsFetched]);

  return (
    <div className="mt-4">
      <h4>Available Seats</h4>
      {error && <div className="alert alert-danger">{error}</div>}
      <ul className="list-group">
        {seats.length > 0 ? (
          seats.map((seat) => (
            <li key={seat.id} className="list-group-item">
              Seat {seat.number} - {seat.booked ? "Booked" : "Available"}
            </li>
          ))
        ) : (
          <li className="list-group-item">No seats available.</li>
        )}
      </ul>
    </div>
  );
};

export default SeatAvailability;
