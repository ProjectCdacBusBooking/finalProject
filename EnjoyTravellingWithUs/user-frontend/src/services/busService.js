// src/services/busService.js

import axios from "axios";

const API_URL = "http://localhost:8080/api/buses";

// Fetch all buses
export const getAllBuses = async () => {
  try {
    const response = await axios.get(`${API_URL}/all`);
    return response.data;
  } catch (error) {
    console.error("Error fetching buses", error);
    throw error;
  }
};

// Fetch a specific bus by ID
export const getBusById = async (busId) => {
  try {
    const response = await axios.get(`${API_URL}/${busId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching bus details", error);
    throw error;
  }
};

// Add a new bus (For Admins)
export const addBus = async (busData) => {
  try {
    const response = await axios.post(`${API_URL}/add`, busData);
    return response.data;
  } catch (error) {
    console.error("Error adding bus", error);
    throw error;
  }
};

const API_BASE_URL = "http://localhost:8080/api/buses";

// Get Available Buses (Search Function)
export const searchBuses = async (source, destination, date) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/search`, {
      params: { source, destination, date },
    });
    return response.data; // Returns list of buses
  } catch (error) {
    console.error("Bus Search Error:", error.response?.data || error.message);
    throw error;
  }
};
