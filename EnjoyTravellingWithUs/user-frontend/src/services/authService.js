import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/users";

// Register User
export const registerUser = async (userData) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/register`, userData);
    return response.data; // Expecting { id, firstName, lastName, email, token }
  } catch (error) {
    console.error("Registration Error:", error.response?.data || error.message);
    throw error;
  }
};

// Login User
export const loginUser = async (credentials) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/login`, credentials);
    return response.data; // Expecting { id, firstName, lastName, email, token }
  } catch (error) {
    console.error("Login Error:", error.response?.data || error.message);
    throw error;
  }
};
