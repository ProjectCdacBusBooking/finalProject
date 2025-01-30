// 📂 src/screens/Booking.jsx

import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom"; // Replace useHistory with useNavigate
import axios from "axios";

function Booking() {
  const { busId } = useParams();
  const navigate = useNavigate(); // Initialize navigate
  //   const [bus, setBus] = useState(null);
  const [seats, setSeats] = useState([]);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchSeats = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/bus/${busId}/seats`
        );
        setSeats(response.data);
      } catch (err) {
        setError("Error fetching seat details. Please try again.");
      } finally {
        setLoading(false);
      }
    };

    fetchSeats();
  }, [busId]);

  const handleSeatSelection = (seat) => {
    if (selectedSeats.includes(seat)) {
      setSelectedSeats(selectedSeats.filter((s) => s !== seat));
    } else {
      setSelectedSeats([...selectedSeats, seat]);
    }
  };

  const handleConfirmBooking = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/bookings/select-seats",
        {
          busId,
          seats: selectedSeats,
        }
      );
      if (response.data.success) {
        navigate(`/confirm-booking/${response.data.bookingId}`); // Use navigate to push to confirmation page
      }
    } catch (err) {
      setError("Error processing booking. Please try again.");
    }
  };

  return (
    <div className="container mt-4">
      {loading ? (
        <div className="text-center mt-4">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      ) : error ? (
        <div className="alert alert-danger">{error}</div>
      ) : (
        <div className="card shadow p-4">
          <h2 className="text-center text-primary">Select Your Seats</h2>
          <div className="seat-layout d-flex flex-wrap">
            {seats.map((seat, index) => (
              <button
                key={index}
                className={`seat ${
                  selectedSeats.includes(seat) ? "selected" : ""
                }`}
                onClick={() => handleSeatSelection(seat)}
                disabled={seat.booked}
              >
                {seat.number}
              </button>
            ))}
          </div>
          <button
            className="btn btn-success w-100 mt-3"
            onClick={handleConfirmBooking}
            disabled={!selectedSeats.length}
          >
            Confirm Booking
          </button>
        </div>
      )}
    </div>
  );
}

export default Booking;
