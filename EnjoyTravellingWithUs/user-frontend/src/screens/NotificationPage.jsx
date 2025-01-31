/*
import React, { useState, useEffect } from "react";
import axios from "axios";

function NotificationsPage() {
  const [notifications, setNotifications] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchNotifications = async () => {
      try {
        const userId = localStorage.getItem("userId");
        if (!userId) {
          setError("User ID is not available.");
          setLoading(false);
          return;
        }
        const response = await axios.get(
          `http://localhost:8080/notifications/${userId}`
        );
        setNotifications(response.data);
      } catch (err) {
        setError("Error fetching notifications.");
      }
      setLoading(false);
    };

    fetchNotifications();
  }, []);

  const handleMarkAsRead = async (notificationId) => {
    try {
      await axios.put(
        `http://localhost:8080/notifications/read/${notificationId}`
      );
      setNotifications((prevNotifications) =>
        prevNotifications.map((notification) =>
          notification.id === notificationId
            ? { ...notification, status: "Read" }
            : notification
        )
      );
    } catch (err) {
      setError("Error marking notification as read.");
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="text-center">Push Notifications</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        {loading ? (
          <div>Loading...</div>
        ) : (
          <div>
            <h4>Notifications</h4>
            {notifications.length === 0 ? (
              <p>No notifications available.</p>
            ) : (
              <ul className="list-group">
                {notifications.map((notification, index) => (
                  <li
                    key={index}
                    className={`list-group-item ${
                      notification.status === "Read" ? "bg-light" : ""
                    }`}
                  >
                    {notification.message}
                    <button
                      className="btn btn-sm btn-primary float-right"
                      onClick={() => handleMarkAsRead(notification.id)}
                    >
                      Mark as Read
                    </button>
                  </li>
                ))}
              </ul>
            )}
          </div>
        )}
      </div>
    </div>
  );
}

export default NotificationsPage;
*/

// 📂 src/screens/NotificationsPage.jsx

import React, { useState, useEffect } from "react";
import axios from "axios";

function NotificationsPage({ match }) {
  const [notifications, setNotifications] = useState([]);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const userId = match?.params?.userId;

  useEffect(() => {
    if (!userId) {
      setError("User ID is missing or invalid.");
      return;
    }

    // Fetch notifications for the user
    const fetchNotifications = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/notifications/${userId}`
        );
        setNotifications(response.data.notifications);
      } catch (err) {
        setError("Error fetching notifications.");
      }
    };

    fetchNotifications();
  }, [userId]);

  const markAsRead = async (notificationId) => {
    try {
      await axios.put(
        `http://localhost:8080/api/notifications/read/${notificationId}`
      );
      setMessage("Notification marked as read!");
      setNotifications(
        notifications.map((notif) =>
          notif.id === notificationId ? { ...notif, status: "read" } : notif
        )
      );
    } catch (err) {
      setError("Error marking notification as read.");
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="text-center">Notifications</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        {message && <div className="alert alert-success">{message}</div>}
        <div>
          <ul className="list-group">
            {notifications.map((notification) => (
              <li
                key={notification.id}
                className={`list-group-item ${
                  notification.status === "read" ? "bg-light" : ""
                }`}
              >
                <div>
                  <strong>{notification.title}</strong>
                  <p>{notification.message}</p>
                  <small>{notification.timestamp}</small>
                  {notification.status === "unread" && (
                    <button
                      className="btn btn-secondary btn-sm"
                      onClick={() => markAsRead(notification.id)}
                    >
                      Mark as Read
                    </button>
                  )}
                </div>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default NotificationsPage;

/*

import React, { useState, useEffect } from "react";
import NotificationService from "../services/NotificationService";

const Notifications = ({ userId }) => {
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    // Fetch notifications when the component mounts
    const fetchNotifications = async () => {
      try {
        const data = await NotificationService.getNotifications(userId);
        setNotifications(data);
      } catch (error) {
        console.error("Failed to load notifications:", error);
      }
    };

    fetchNotifications();
  }, [userId]);

  const handleMarkAsRead = async (notificationId) => {
    try {
      await NotificationService.markNotificationsAsRead(notificationId);
      setNotifications((prevNotifications) =>
        prevNotifications.filter((notif) => notif.id !== notificationId)
      );
    } catch (error) {
      console.error("Error marking notification as read:", error);
    }
  };

  return (
    <div className="notifications-container">
      <h3>Your Notifications</h3>
      {notifications.length === 0 ? (
        <p>No notifications yet.</p>
      ) : (
        <ul>
          {notifications.map((notif) => (
            <li key={notif.id}>
              <p>{notif.message}</p>
              <button onClick={() => handleMarkAsRead(notif.id)}>
                Mark as Read
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Notifications;


*/
