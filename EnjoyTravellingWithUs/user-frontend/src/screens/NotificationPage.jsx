import React, { useEffect, useState } from "react";
import axios from "axios";

const NotificationPage = () => {
  const [notifications, setNotifications] = useState([]);
  const [error, setError] = useState("");
  const user = JSON.parse(localStorage.getItem("user")); // ✅ Get user data from local storage
  const userId = user?.id; // ✅ Extract user ID

  useEffect(() => {
    if (!userId) {
      setError("User not logged in!");
      return;
    }

    const fetchNotifications = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/notifications/user/${userId}`
        );
        setNotifications(response.data);
      } catch (err) {
        console.error("❌ Notification Fetch Error:", err);
        setError("Error loading notifications.");
      }
    };

    fetchNotifications();
  }, [userId]);

  return (
    <div className="container">
      <h3 className="mt-3">🔔 Notifications</h3>
      {error && <div className="alert alert-danger">{error}</div>}
      <ul className="list-group mt-3">
        {notifications.length > 0 ? (
          notifications.map((notif, index) => (
            <li key={index} className="list-group-item">
              {notif.message}
            </li>
          ))
        ) : (
          <li className="list-group-item">No notifications available.</li>
        )}
      </ul>
    </div>
  );
};

export default NotificationPage;
