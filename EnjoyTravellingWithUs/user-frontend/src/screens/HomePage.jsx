import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function HomePage() {
  const [source, setSource] = useState("");
  const [destination, setDestination] = useState("");
  const [date, setDate] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSearch = (e) => {
    e.preventDefault();
    if (!source || !destination || !date) {
      setError("Please fill all fields.");
      return;
    }

    navigate(
      `/search?source=${source}&destination=${destination}&date=${date}`
    );
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center text-primary">Search for Buses</h2>
      <div className="card shadow p-4 mt-4">
        {error && <div className="alert alert-danger">{error}</div>}
        <form onSubmit={handleSearch}>
          <div className="mb-3">
            <label className="form-label fw-bold">Source</label>
            <input
              type="text"
              className="form-control"
              value={source}
              onChange={(e) => setSource(e.target.value)}
              placeholder="Enter source city"
            />
          </div>
          <div className="mb-3">
            <label className="form-label fw-bold">Destination</label>
            <input
              type="text"
              className="form-control"
              value={destination}
              onChange={(e) => setDestination(e.target.value)}
              placeholder="Enter destination city"
            />
          </div>
          <div className="mb-3">
            <label className="form-label fw-bold">Travel Date</label>
            <input
              type="date"
              className="form-control"
              value={date}
              onChange={(e) => setDate(e.target.value)}
            />
          </div>
          <button type="submit" className="btn btn-primary w-100">
            Search Buses
          </button>
        </form>
      </div>
    </div>
  );
}

export default HomePage;
