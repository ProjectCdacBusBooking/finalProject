// 📂 src/screens/BookingCancellationPage.jsx

import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";

function BookingCancellationPage() {
  const [booking, setBooking] = useState({});
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const { bookingId } = useParams();
  const navigate = useNavigate(); // useNavigate instead of useHistory

  useEffect(() => {
    // Fetch booking details by booking ID
    const fetchBookingDetails = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/bookings/${bookingId}`
        );
        setBooking(response.data);
      } catch (err) {
        setError("Error fetching booking details. Please try again.");
      }
    };

    fetchBookingDetails();
  }, [bookingId]);

  const handleCancelBooking = async () => {
    try {
      const userId = localStorage.getItem("userId");

      // Check if user is logged in before proceeding
      if (!userId) {
        setError("User is not logged in.");
        navigate("/login"); // Redirect to login page if user is not logged in
        return;
      }

      const response = await axios.post(
        `http://localhost:8080/bookings/cancel/${bookingId}`
      );
      setSuccess(
        "Booking cancelled successfully. A refund will be processed shortly."
      );

      // Redirect to booking history after successful cancellation
      setTimeout(() => {
        navigate("/booking-history");
      }, 2000);
    } catch (err) {
      setError(
        err.response
          ? err.response.data.message
          : "Error cancelling booking. Please try again."
      );
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="text-center">Booking Cancellation</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        {success && <div className="alert alert-success">{success}</div>}
        <div>
          <h5>Bus: {booking.busName}</h5>
          <p>Date: {booking.date}</p>
          <p>Seats: {booking.seats}</p>
          <p>Status: {booking.status}</p>
        </div>
        <button onClick={handleCancelBooking} className="btn btn-danger">
          Cancel Booking
        </button>
      </div>
    </div>
  );
}

export default BookingCancellationPage;
