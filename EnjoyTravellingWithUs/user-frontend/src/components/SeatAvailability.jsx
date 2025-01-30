import React, { useState, useEffect } from "react";
import axios from "axios";

const SeatAvailability = ({ busId, selectedDate }) => {
  const [seatData, setSeatData] = useState(null);

  useEffect(() => {
    // Fetch seat availability when the busId and selectedDate change
    const fetchSeatData = async () => {
      try {
        const response = await axios.get(
          `/api/seat-availability/${busId}/${selectedDate}`
        );
        setSeatData(response.data);
      } catch (error) {
        console.error("Error fetching seat availability", error);
      }
    };

    fetchSeatData();
  }, [busId, selectedDate]);

  return (
    <div className="seat-availability">
      {seatData ? (
        <div>
          <h3>Seats Available</h3>
          <p>Total Seats: {seatData.totalSeats}</p>
          <p>Available Seats: {seatData.availableSeats}</p>

          {/* Seat selection logic */}
          <div className="seats">
            {/* Simple visualization of seat availability */}
            {Array.from({ length: seatData.totalSeats }).map((_, index) => (
              <div
                key={index}
                className={`seat ${
                  index < seatData.availableSeats ? "available" : "unavailable"
                }`}
              >
                {index + 1}
              </div>
            ))}
          </div>
        </div>
      ) : (
        <p>Loading seat availability...</p>
      )}
    </div>
  );
};

export default SeatAvailability;
