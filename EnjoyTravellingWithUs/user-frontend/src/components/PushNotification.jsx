import React, { useState, useEffect } from "react";

const PushNotification = () => {
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    // Connect to WebSocket server
    const socket = new WebSocket("ws://localhost:8080/ws/notifications");

    // Listen for messages from the server
    socket.onmessage = (event) => {
      setNotifications((prevNotifications) => [
        ...prevNotifications,
        event.data,
      ]);
    };

    // Close the WebSocket connection when the component unmounts
    return () => {
      socket.close();
    };
  }, []);

  return (
    <div className="notifications">
      <h3>Notifications</h3>
      <ul>
        {notifications.map((notification, index) => (
          <li key={index}>{notification}</li>
        ))}
      </ul>
    </div>
  );
};

export default PushNotification;
