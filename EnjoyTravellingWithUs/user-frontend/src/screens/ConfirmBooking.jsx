import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

function ConfirmBooking() {
  const { bookingId } = useParams();
  const navigate = useNavigate();
  const [booking, setBooking] = useState(null);
  const [walletBalance, setWalletBalance] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchBookingDetails = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/bookings/${bookingId}`
        );
        setBooking(response.data);
        const walletRes = await axios.get(
          `http://localhost:8080/api/wallet/${response.data.userId}`
        );
        setWalletBalance(walletRes.data.balance);
      } catch (err) {
        setError("Error fetching booking details.");
      } finally {
        setLoading(false);
      }
    };

    fetchBookingDetails();
  }, [bookingId]);

  const handlePayment = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/wallet/pay",
        { bookingId, amount: booking.totalFare }
      );

      if (response.data.success) {
        navigate(`/booking-success/${bookingId}`);
      } else {
        setError("Insufficient balance. Add money to wallet.");
      }
    } catch (err) {
      setError("Payment failed. Try again.");
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
          <h2 className="text-center text-primary">Confirm Your Booking</h2>
          <div>
            <p>
              <strong>Bus:</strong> {booking.busName}
            </p>
            <p>
              <strong>Date:</strong> {booking.date}
            </p>
            <p>
              <strong>Seats:</strong> {booking.seats.join(", ")}
            </p>
            <p>
              <strong>Total Fare:</strong> ₹{booking.totalFare}
            </p>
            <p>
              <strong>Wallet Balance:</strong> ₹{walletBalance}
            </p>
          </div>
          <button
            className="btn btn-success w-100 mt-3"
            onClick={handlePayment}
            disabled={walletBalance < booking.totalFare}
          >
            Pay via Wallet
          </button>
        </div>
      )}
    </div>
  );
}

export default ConfirmBooking;
