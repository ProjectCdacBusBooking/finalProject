import React, { useState, useEffect } from "react";
import axios from "axios";

const SeatAvailability = ({ busId, selectedDate, onSeatsFetched }) => {
  const [seatData, setSeatData] = useState(null);

  useEffect(() => {
    // Fetch available seats from API
    const fetchSeatData = async () => {
      try {
        const response = await axios.get(
          `/api/seat-availability/${busId}/${selectedDate}`
        );
        setSeatData(response.data);
        onSeatsFetched(response.data.availableSeats); // Pass data to parent component
      } catch (error) {
        console.error("Error fetching seat availability", error);
      }
    };

    fetchSeatData();
  }, [busId, selectedDate, onSeatsFetched]);

  return (
    <div className="seat-availability">
      {seatData ? (
        <div>
          <h3>Seats Available</h3>
          <p>Total Seats: {seatData.totalSeats}</p>
          <p>Available Seats: {seatData.availableSeats.length}</p>

          {/* Display available seats */}
          <div className="seats">
            {seatData.availableSeats.map((seat, index) => (
              <div key={index} className="seat available">
                {seat}
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
