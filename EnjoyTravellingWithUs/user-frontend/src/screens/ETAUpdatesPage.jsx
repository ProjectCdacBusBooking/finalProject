// 📂 src/screens/ETAUpdatesPage.jsx

import React, { useState, useEffect } from "react";
import axios from "axios";

function ETAUpdatesPage({ match }) {
  const [eta, setEta] = useState(null);
  const [error, setError] = useState("");
  const busId = match?.params?.busId; // Handling undefined match

  useEffect(() => {
    if (!busId) {
      setError("Bus ID is missing or invalid.");
      return;
    }

    // Fetch ETA updates for the bus
    const fetchETA = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/buses/${busId}/eta`
        );
        setEta(response.data.eta);
      } catch (err) {
        setError("Error fetching ETA.");
      }
    };

    fetchETA();
    const interval = setInterval(fetchETA, 60000); // Update every 60 seconds

    return () => clearInterval(interval); // Cleanup interval on unmount
  }, [busId]);

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="text-center">Estimated Arrival Time (ETA)</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        {eta ? (
          <div>
            <p>
              <strong>Estimated Arrival Time:</strong> {eta}
            </p>
          </div>
        ) : (
          <div>Loading...</div>
        )}
      </div>
    </div>
  );
}

export default ETAUpdatesPage;
