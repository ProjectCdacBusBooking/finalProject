// src/services/profileService.js

import axios from "axios";

const API_URL = "http://localhost:8080/users";

export const getUserProfile = async () => {
  const token = localStorage.getItem("token");
  const response = await axios.get(`${API_URL}/profile`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

export const updateUserProfile = async (data) => {
  const token = localStorage.getItem("token");
  const response = await axios.put(`${API_URL}/update`, data, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};
