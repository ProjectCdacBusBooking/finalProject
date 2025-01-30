// 📂 src/screens/BusDetails.jsx

import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

function BusDetails() {
  const { busId } = useParams();
  const navigate = useNavigate();
  const [bus, setBus] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchBusDetails = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/buses/${busId}`
        );
        setBus(response.data);
      } catch (err) {
        setError("Error fetching bus details. Please try again.");
      } finally {
        setLoading(false);
      }
    };

    fetchBusDetails();
  }, [busId]);

  const handleBookNow = () => {
    navigate(`/book/${busId}`);
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
      ) : bus ? (
        <div className="card shadow p-4">
          <h2 className="text-center text-primary">{bus.name}</h2>
          <p className="text-center">
            <strong>Route:</strong> {bus.source} → {bus.destination} <br />
            <strong>Departure:</strong> {bus.departureTime} <br />
            <strong>Fare:</strong> ₹{bus.fare} <br />
          </p>
          <button className="btn btn-success w-100" onClick={handleBookNow}>
            Book Now
          </button>
        </div>
      ) : (
        <div className="alert alert-warning">No bus details found.</div>
      )}
    </div>
  );
}

export default BusDetails;
