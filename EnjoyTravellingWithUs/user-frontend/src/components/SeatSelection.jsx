import React, { useState } from "react";
import axios from "axios";

const SeatSelection = ({ busId, selectedDate, availableSeats }) => {
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [bookingMessage, setBookingMessage] = useState("");

  // Handle seat selection
  const handleSeatSelection = (seatNumber) => {
    setSelectedSeats((prevSelectedSeats) => {
      if (prevSelectedSeats.includes(seatNumber)) {
        return prevSelectedSeats.filter((seat) => seat !== seatNumber);
      } else {
        return [...prevSelectedSeats, seatNumber];
      }
    });
  };

  // Handle booking confirmation
  const confirmBooking = async () => {
    try {
      const bookingData = {
        busId,
        date: selectedDate,
        seatNumbers: selectedSeats.join(","), // Convert array to comma-separated string
      };

      const response = await axios.post("/api/book-seat", bookingData);
      setBookingMessage(response.data.message);
    } catch (error) {
      setBookingMessage("Error booking the seat. Please try again.");
    }
  };

  return (
    <div className="seat-selection">
      <h3>Select Your Seats</h3>
      <div className="seats">
        {Array.isArray(availableSeats) ? (
          availableSeats.map((seat, index) => (
            <div
              key={index}
              className={`seat ${
                selectedSeats.includes(seat) ? "selected" : "available"
              }`}
              onClick={() => handleSeatSelection(seat)}
            >
              {seat}
            </div>
          ))
        ) : (
          <p>Loading seats...</p>
        )}
      </div>

      <button className="confirm-booking" onClick={confirmBooking}>
        Confirm Booking
      </button>

      {bookingMessage && <p>{bookingMessage}</p>}
    </div>
  );
};

export default SeatSelection;
