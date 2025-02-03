import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

function Booking() {
  const { busId } = useParams();
  const navigate = useNavigate();
  const [seats, setSeats] = useState([]);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [booking, setBooking] = useState(null);

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
        fetchBookingDetails(response.data.bookingId);
        // Optional: navigate(`/confirm-booking/${response.data.bookingId}`);
      }
    } catch (err) {
      setError("Error processing booking. Please try again.");
    }
  };

  const fetchBookingDetails = async (bookingId) => {
    try {
      const response = await axios.get(`/bookings/${bookingId}`); // Replace with your API endpoint
      setBooking(response.data);
    } catch (error) {
      console.error("Error fetching booking details:", error);
      setError("Error fetching booking details.");
    }
  };

  const handleCancelBooking = async () => {
    if (!booking) return;
    try {
      await axios.put(`/bookings/cancel/${booking.id}`); // Replace with your API endpoint
      alert("Booking cancelled successfully!");
      setBooking(null);
      setSelectedSeats([]); // Optional: Clear selected seats
    } catch (error) {
      alert("Error cancelling booking. Please try again.");
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
      ) : booking ? (
        <div>
          <h3>Booking Details</h3>
          <p>Source: {booking.source}</p> {/* Replace with your booking data */}
          <p>Destination: {booking.destination}</p>
          <p>Travel Date: {booking.travelDate}</p>
          <button onClick={handleCancelBooking} className="btn btn-danger">
            Cancel Booking
          </button>
        </div>
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
