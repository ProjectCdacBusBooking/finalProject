import React, { useEffect, useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

const SeatSelection = ({ busId, onSeatsSelected }) => {
  const [seats, setSeats] = useState([]);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchSeats = async () => {
      try {
        console.log(`📌 Fetching seats for Bus ID: ${busId}`);
        const response = await axios.get(
          `http://localhost:8080/api/buses/${busId}/seats`
        );
        setSeats(response.data);
      } catch (err) {
        console.error("❌ Error fetching seats:", err);
        setError("Error loading seats. Please try again.");
      }
    };

    fetchSeats();
  }, [busId]);

  const handleSeatClick = (seatNumber) => {
    let updatedSeats;
    if (selectedSeats.includes(seatNumber)) {
      updatedSeats = selectedSeats.filter((s) => s !== seatNumber);
    } else {
      updatedSeats = [...selectedSeats, seatNumber];
    }
    setSelectedSeats(updatedSeats);
    onSeatsSelected(updatedSeats); // ✅ Ensure latest selection is sent
  };

  const confirmSeats = () => {
    if (selectedSeats.length === 0) {
      alert("Please select at least one seat.");
      return;
    }
    alert(`Seats Selected: ${selectedSeats.join(", ")}`);
  };

  return (
    <div className="container text-center">
      <h3>Select Your Seats</h3>
      {error && <div className="alert alert-danger">{error}</div>}

      <div className="seat-layout">
        {seats.length > 0 ? (
          seats.map((seat) => (
            <button
              key={seat.number}
              className={`seat btn ${
                seat.booked ? "btn-danger" : "btn-light"
              } ${selectedSeats.includes(seat.number) ? "btn-success" : ""}`}
              onClick={() => handleSeatClick(seat.number)}
              disabled={seat.booked}
            >
              {seat.number}
            </button>
          ))
        ) : (
          <p>No seats available.</p>
        )}
      </div>

      <button className="btn btn-primary mt-3" onClick={confirmSeats}>
        Confirm Selection
      </button>

      <style>
        {`
          .seat-layout {
            display: grid;
            grid-template-columns: repeat(4, 50px);
            gap: 10px;
            justify-content: center;
          }
          .seat {
            width: 50px;
            height: 50px;
            font-size: 14px;
            text-align: center;
            line-height: 50px;
            border-radius: 5px;
          }
          .btn-danger {
            background-color: red;
            cursor: not-allowed;
          }
          .btn-success {
            background-color: green;
          }
        `}
      </style>
    </div>
  );
};

export default SeatSelection;
