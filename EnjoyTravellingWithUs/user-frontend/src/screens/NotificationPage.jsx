import React, { useEffect, useState } from "react";
import {
  getUserNotifications,
  markNotificationAsRead,
} from "../services/notificationService";

const NotificationPage = () => {
  const userId = localStorage.getItem("userId");
  const [notifications, setNotifications] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchNotifications();
  }, []);

  const fetchNotifications = async () => {
    try {
      const data = await getUserNotifications(userId);
      setNotifications(data);
    } catch (error) {
      setError("Error fetching notifications.");
    }
  };

  const handleMarkAsRead = async (notificationId) => {
    await markNotificationAsRead(notificationId);
    fetchNotifications(); // Refresh list after marking read
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="text-center">Notifications</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        <ul>
          {notifications.map((notif) => (
            <li key={notif.id}>
              {notif.message}
              <button
                className="btn btn-sm btn-primary ml-2"
                onClick={() => handleMarkAsRead(notif.id)}
              >
                Mark as Read
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default NotificationPage;
