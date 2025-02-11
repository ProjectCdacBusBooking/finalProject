import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/Booking.css";
import { FaBus, FaTicketAlt, FaCheckCircle } from "react-icons/fa";

const Booking = () => {
  const navigate = useNavigate();
  const [buses, setBuses] = useState([]);
  const [selectedBus, setSelectedBus] = useState(null);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [walletBalance, setWalletBalance] = useState(0);
  const [paymentMethod, setPaymentMethod] = useState("wallet");

  // Get user from localStorage (Check if logged in)
  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    if (!user) {
      navigate("/login"); // Redirect if not logged in
    } else {
      fetchBuses();
      fetchWalletBalance();
    }
  }, [user]);

  const fetchBuses = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/buses/all");
      setBuses(response.data);
    } catch (error) {
      console.error("Error fetching buses:", error);
    }
  };

  const fetchWalletBalance = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/wallets/user/${user.id}`
      );
      setWalletBalance(response.data.balance);
    } catch (error) {
      console.error("Error fetching wallet balance:", error);
    }
  };

  const handleBusSelect = (bus) => {
    setSelectedBus(bus);
    setSelectedSeats([]);
  };

  const handleSeatSelect = (seat) => {
    if (selectedSeats.includes(seat)) {
      setSelectedSeats(selectedSeats.filter((s) => s !== seat));
    } else {
      setSelectedSeats([...selectedSeats, seat]);
    }
  };

  const calculateTotalPrice = () => {
    return selectedSeats.length * (selectedBus ? selectedBus.fare : 0);
  };

  const confirmBooking = async () => {
    if (selectedSeats.length === 0) {
      alert("Please select at least one seat.");
      return;
    }

    const totalPrice = calculateTotalPrice();

    if (paymentMethod === "wallet" && totalPrice > walletBalance) {
      alert(
        "Insufficient wallet balance! Please choose another payment method."
      );
      return;
    }

    try {
      const bookingRequest = {
        userId: user.id,
        busId: selectedBus.id,
        selectedSeats: selectedSeats.length,
        seatNumbers: selectedSeats,
        paymentMethod,
      };

      await axios.post(
        "http://localhost:8080/api/bookings/create",
        bookingRequest
      );
      alert("✅ Booking Successful!");
      navigate("/confirm-booking"); // Redirect to confirmation page
    } catch (error) {
      console.error("Error booking tickets:", error);
    }
  };

  return (
    <div className="booking-container">
      <h2>🚌 Book Your Bus Tickets</h2>

      <section className="bus-selection">
        <h3>Available Buses</h3>
        <div className="bus-list">
          {buses.map((bus) => (
            <div
              key={bus.id}
              className="bus-card"
              onClick={() => handleBusSelect(bus)}
            >
              <FaBus className="bus-icon" />
              <h3>
                {bus.source} ➝ {bus.destination}
              </h3>
              <p>🕒 Departure: {bus.departureTime}</p>
              <p>💰 Fare: ₹{bus.fare} per seat</p>
            </div>
          ))}
        </div>
      </section>

      {selectedBus && (
        <section className="seat-selection">
          <h3>Select Your Seats</h3>
          <div className="seats-grid">
            {[...Array(selectedBus.totalSeats)].map((_, i) => {
              const seatNum = `S${i + 1}`;
              return (
                <div
                  key={seatNum}
                  className={`seat ${
                    selectedSeats.includes(seatNum) ? "selected" : ""
                  }`}
                  onClick={() => handleSeatSelect(seatNum)}
                >
                  {seatNum}
                </div>
              );
            })}
          </div>
        </section>
      )}

      {selectedSeats.length > 0 && (
        <section className="payment-section">
          <h3>Payment Method</h3>
          <p>Wallet Balance: ₹{walletBalance}</p>
          <select
            value={paymentMethod}
            onChange={(e) => setPaymentMethod(e.target.value)}
          >
            <option value="wallet">Wallet</option>
            <option value="credit-card">Credit Card</option>
          </select>
          <p>Total Price: ₹{calculateTotalPrice()}</p>
          <button className="confirm-button" onClick={confirmBooking}>
            <FaCheckCircle /> Confirm Booking
          </button>
        </section>
      )}
    </div>
  );
};

export default Booking;
