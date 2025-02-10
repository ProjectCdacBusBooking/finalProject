// src/services/bookingService.js

import axios from "axios";

const API_URL = "http://localhost:8080/api/bookings";

// Create a new booking
export const createBooking = async (bookingData) => {
  try {
    const response = await axios.post(`${API_URL}/create`, bookingData);
    return response.data;
  } catch (error) {
    console.error("Error creating booking", error);
    throw error;
  }
};

// Fetch all bookings (Admin use-case)
export const getAllBookings = async () => {
  try {
    const response = await axios.get(`${API_URL}/all`);
    return response.data;
  } catch (error) {
    console.error("Error fetching all bookings", error);
    throw error;
  }
};

// Fetch bookings for a specific user
export const getUserBookings = async (userId) => {
  try {
    const response = await axios.get(`${API_URL}/user/${userId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching user bookings", error);
    throw error;
  }
};
