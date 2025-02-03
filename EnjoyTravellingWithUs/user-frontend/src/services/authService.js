// src/services/authService.js

import axios from "axios";

const API_URL = "http://localhost:8080/user";

export const registerUser = async (data) => {
  const response = await axios.post(`${API_URL}/register`, data);
  return response.data;
};

export const loginUser = async (data) => {
  const response = await axios.post(`${API_URL}/login`, data);
  localStorage.setItem("token", response.data.token); // Save token in localStorage
  return response.data;
};
