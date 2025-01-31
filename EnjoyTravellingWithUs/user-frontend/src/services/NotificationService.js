import axios from "axios";

const API_URL = "/api/notifications";

// Function to subscribe to notifications
const subscribeToNotifications = async (userId) => {
  try {
    const response = await axios.post(`${API_URL}/subscribe/${userId}`);
    return response.data;
  } catch (error) {
    console.error("Error subscribing to notifications", error);
    throw error;
  }
};

// Function to get notifications
const getNotifications = async (userId) => {
  try {
    const response = await axios.get(`${API_URL}/${userId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching notifications", error);
    throw error;
  }
};

// Function to mark notifications as read
const markNotificationsAsRead = async (notificationId) => {
  try {
    const response = await axios.put(`${API_URL}/read/${notificationId}`);
    return response.data;
  } catch (error) {
    console.error("Error marking notifications as read", error);
    throw error;
  }
};

export default {
  subscribeToNotifications,
  getNotifications,
  markNotificationsAsRead,
};
