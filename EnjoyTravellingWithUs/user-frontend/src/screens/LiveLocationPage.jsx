// 📂 src/screens/LiveLocationPage.jsx

import React, { useState, useEffect } from "react";
import axios from "axios";

function LiveLocationPage({ match }) {
  const [busLocation, setBusLocation] = useState(null);
  const [error, setError] = useState("");
  const busId = match?.params?.busId; // Handling undefined match

  useEffect(() => {
    if (!busId) {
      setError("Bus ID is missing or invalid.");
      return;
    }

    // Fetch live bus location
    const fetchBusLocation = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/buses/${busId}/live-location`
        );
        setBusLocation(response.data.location);
      } catch (err) {
        setError("Error fetching bus location.");
      }
    };

    fetchBusLocation();
    const interval = setInterval(fetchBusLocation, 5000); // Update every 5 seconds

    return () => clearInterval(interval); // Cleanup interval on unmount
  }, [busId]);

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="text-center">Live Bus Location</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        {busLocation ? (
          <div>
            <p>
              <strong>Latitude:</strong> {busLocation.latitude}
            </p>
            <p>
              <strong>Longitude:</strong> {busLocation.longitude}
            </p>
            {/* You can integrate a map here to show the location */}
            <div id="map">
              {/* Map integration can be done with Google Maps API or any other service */}
            </div>
          </div>
        ) : (
          <div>Loading...</div>
        )}
      </div>
    </div>
  );
}

export default LiveLocationPage;
