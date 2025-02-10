// src/services/profileService.js

import axios from "axios";

const API_URL = "http://localhost:8080/api/users";

export const getUserProfile = async () => {
  try {
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId"); // User ID योग्यरित्या मिळवणे

    if (!token || !userId) {
      throw new Error("User not authenticated");
    }

    const response = await axios.get(`${API_URL}${userId}`, {
      headers: { Authorization: `Bearer ${token}` },
    });

    return response.data;
  } catch (error) {
    console.error("Error fetching user profile:", error);
    throw error;
  }
};

export const updateUserProfile = async (data) => {
  try {
    const token = localStorage.getItem("token");

    if (!token) {
      throw new Error("User not authenticated");
    }

    const response = await axios.put(`${API_URL}/update`, data, {
      headers: { Authorization: `Bearer ${token}` },
    });

    return response.data;
  } catch (error) {
    console.error("Error updating user profile:", error);
    throw error;
  }
};
