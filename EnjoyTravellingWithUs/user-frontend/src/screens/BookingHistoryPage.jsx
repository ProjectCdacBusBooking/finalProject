// 📂 src/screens/BookingHistoryPage.jsx

import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom"; // Import useNavigate for redirection

function BookingHistoryPage() {
  const [bookings, setBookings] = useState([]);
  const [error, setError] = useState("");
  const navigate = useNavigate(); // Initialize useNavigate for redirection

  useEffect(() => {
    // Fetch user's booking history
    const fetchBookingHistory = async () => {
      try {
        const userId = localStorage.getItem("userId");

        // If userId is not found in localStorage, redirect to login
        if (!userId) {
          setError("User is not logged in.");
          navigate("/login"); // Redirect to login page
          return;
        }

        const response = await axios.get(
          `http://localhost:8080/bookings/history/${userId}`
        );
        setBookings(response.data);
      } catch (err) {
        // Display actual error message if available
        setError(
          err.response
            ? err.response.data.message
            : "Error fetching booking history. Please try again."
        );
      }
    };

    fetchBookingHistory();
  }, [navigate]); // Added navigate as a dependency to ensure redirection works correctly

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="text-center">Booking History</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        {bookings.length > 0 ? (
          <div>
            <ul className="list-group">
              {bookings.map((booking) => (
                <li className="list-group-item" key={booking.bookingId}>
                  <div>
                    <h5>{booking.busName}</h5>
                    <p>Date: {booking.date}</p>
                    <p>Seats: {booking.seats}</p>
                    <p>Status: {booking.status}</p>
                  </div>
                </li>
              ))}
            </ul>
          </div>
        ) : (
          <p>No bookings found.</p>
        )}
      </div>
    </div>
  );
}

export default BookingHistoryPage;
