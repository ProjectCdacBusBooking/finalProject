import React, { useState, useEffect } from "react";
import axios from "axios";

const BookingHistory = ({ userId }) => {
  const [bookingHistory, setBookingHistory] = useState([]);
  const [cancelMessage, setCancelMessage] = useState("");

  useEffect(() => {
    // Fetch the booking history when the component mounts
    const fetchBookingHistory = async () => {
      try {
        const response = await axios.get(`/api/booking-history/${userId}`);
        setBookingHistory(response.data);
      } catch (error) {
        console.error("Error fetching booking history", error);
      }
    };
    fetchBookingHistory();
  }, [userId]);

  // Handle booking cancellation
  const cancelBooking = async (bookingId) => {
    try {
      const cancelData = { bookingId };
      const response = await axios.post("/api/cancel-booking", cancelData);
      setCancelMessage(response.data);
      setBookingHistory(
        bookingHistory.filter((booking) => booking.bookingId !== bookingId)
      ); // Remove the cancelled booking
    } catch (error) {
      console.error("Error cancelling booking", error);
    }
  };

  return (
    <div className="booking-history">
      <h3>Your Booking History</h3>
      {cancelMessage && <p>{cancelMessage}</p>}
      <ul>
        {bookingHistory.map((booking) => (
          <li key={booking.bookingId}>
            <p>Booking ID: {booking.bookingId}</p>
            <p>Date: {booking.date}</p>
            <p>Seats: {booking.seatNumbers}</p>
            <button onClick={() => cancelBooking(booking.bookingId)}>
              Cancel Booking
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BookingHistory;
