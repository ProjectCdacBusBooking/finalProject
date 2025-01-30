// 📂 src/screens/BookingSuccess.jsx

import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom"; // Changed from useHistory to useNavigate
import axios from "axios";

function BookingSuccess() {
  const { bookingId } = useParams();
  const navigate = useNavigate(); // useNavigate instead of useHistory
  const [bookingDetails, setBookingDetails] = useState(null);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchBookingDetails = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/bookings/${bookingId}`
        );
        setBookingDetails(response.data);
      } catch (err) {
        setError("Error fetching booking details. Please try again.");
      }
    };

    fetchBookingDetails();
  }, [bookingId]);

  return (
    <div className="container mt-4">
      {error ? (
        <div className="alert alert-danger">{error}</div>
      ) : bookingDetails ? (
        <div className="card shadow p-4">
          <h2 className="text-center text-success">Booking Successful!</h2>
          <div>
            <p>
              <strong>Booking ID:</strong> {bookingDetails.bookingId}
            </p>
            <p>
              <strong>Bus:</strong> {bookingDetails.busName}
            </p>
            <p>
              <strong>Date:</strong> {bookingDetails.date}
            </p>
            <p>
              <strong>Seats:</strong> {bookingDetails.seats.join(", ")}
            </p>
            <p>
              <strong>Total Fare:</strong> ₹{bookingDetails.totalFare}
            </p>
          </div>
          <div className="d-flex justify-content-between mt-4">
            <button
              className="btn btn-success"
              onClick={() => navigate("/booking-history")}
            >
              View Booking History
            </button>
            <button className="btn btn-primary" onClick={() => navigate("/")}>
              Go to Home
            </button>
          </div>
        </div>
      ) : (
        <div className="text-center">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      )}
    </div>
  );
}

export default BookingSuccess;
