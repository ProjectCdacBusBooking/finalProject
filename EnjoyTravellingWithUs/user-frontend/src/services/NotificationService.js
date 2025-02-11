import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/notifications";

// Get User Notifications
export const getUserNotifications = async (userId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/user/${userId}`);
    return response.data;
  } catch (error) {
    console.error(
      "Notification Fetch Error:",
      error.response?.data || error.message
    );
    throw error;
  }
};

// Mark Notification as Read
export const markNotificationAsRead = async (notificationId) => {
  try {
    const response = await axios.put(`${API_BASE_URL}/read/${notificationId}`);
    return response.data; // Explicitly return response
  } catch (error) {
    console.error("Mark as Read Error:", error.response?.data || error.message);
    throw error;
  }
};
