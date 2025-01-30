import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

function SearchResults() {
  const location = useLocation();
  const navigate = useNavigate(); // Use navigate instead of useHistory
  const [buses, setBuses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchBuses = async () => {
      const queryParams = new URLSearchParams(location.search);
      const source = queryParams.get("source");
      const destination = queryParams.get("destination");
      const date = queryParams.get("date");

      if (!source || !destination || !date) {
        setError("Invalid search parameters.");
        setLoading(false);
        return;
      }

      try {
        const response = await axios.get(
          `http://localhost:8080/buses/search?source=${source}&destination=${destination}&date=${date}`
        );
        setBuses(response.data);
      } catch (err) {
        setError("Error fetching buses. Please try again.");
      } finally {
        setLoading(false);
      }
    };

    fetchBuses();
  }, [location.search]);

  const handleViewDetails = (busId) => {
    navigate(`/bus/${busId}`); // Use navigate instead of history.push
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center text-primary">Available Buses</h2>
      {loading ? (
        <div className="text-center mt-4">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      ) : error ? (
        <div className="alert alert-danger">{error}</div>
      ) : buses.length === 0 ? (
        <div className="alert alert-warning text-center">
          No buses available for this route.
        </div>
      ) : (
        <div className="row mt-3">
          {buses.map((bus) => (
            <div key={bus.id} className="col-md-4">
              <div className="card shadow mb-3">
                <div className="card-body">
                  <h5 className="card-title">{bus.name}</h5>
                  <p className="card-text">
                    <strong>Route:</strong> {bus.source} → {bus.destination}
                    <br />
                    <strong>Departure:</strong> {bus.departureTime}
                    <br />
                    <strong>Fare:</strong> ₹{bus.fare}
                  </p>
                  <button
                    className="btn btn-primary"
                    onClick={() => handleViewDetails(bus.id)}
                  >
                    View Details
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default SearchResults;
