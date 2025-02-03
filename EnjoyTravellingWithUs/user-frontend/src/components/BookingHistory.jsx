import React, { useState, useEffect } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css"; // Import Bootstrap

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
      const response = await axios.post("/api/cancel-booking", { bookingId });

      if (response.data.success) {
        setCancelMessage("Booking canceled successfully.");
        setBookingHistory((prevHistory) =>
          prevHistory.filter((booking) => booking.bookingId !== bookingId)
        );
      } else {
        setCancelMessage("Failed to cancel booking. Please try again.");
      }
    } catch (error) {
      console.error("Error cancelling booking", error);
      setCancelMessage("Error cancelling booking. Please try again.");
    }
  };

  return (
    <div className="container mt-4">
      <h3 className="text-center mb-4">Your Booking History</h3>

      {cancelMessage && (
        <div className="alert alert-info text-center">{cancelMessage}</div>
      )}

      {bookingHistory.length > 0 ? (
        <div className="table-responsive">
          <table className="table table-bordered table-hover text-center">
            <thead className="table-dark">
              <tr>
                <th>Booking ID</th>
                <th>Date</th>
                <th>Seats</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {bookingHistory.map((booking) => (
                <tr key={booking.bookingId}>
                  <td>{booking.bookingId}</td>
                  <td>{booking.date}</td>
                  <td>
                    {Array.isArray(booking.seatNumbers)
                      ? booking.seatNumbers.join(", ")
                      : booking.seatNumbers}
                  </td>
                  <td>
                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() => cancelBooking(booking.bookingId)}
                    >
                      Cancel Booking
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ) : (
        <div className="alert alert-warning text-center">
          No bookings found.
        </div>
      )}
    </div>
  );
};

export default BookingHistory;
