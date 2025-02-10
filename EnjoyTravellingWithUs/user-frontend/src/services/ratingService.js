import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/reviews";

// Add Review
export const addReview = async (reviewData) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/add`, reviewData);
    return response.data;
  } catch (error) {
    console.error("Add Review Error:", error.response?.data || error.message);
    throw error;
  }
};

// Get Reviews by Bus ID
export const getReviewsByBus = async (busId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/bus/${busId}`);
    return response.data;
  } catch (error) {
    console.error(
      "Fetch Reviews Error:",
      error.response?.data || error.message
    );
    throw error;
  }
};

// Get Average Rating
export const getAverageRating = async (busId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/average/${busId}`);
    return response.data;
  } catch (error) {
    console.error(
      "Fetch Average Rating Error:",
      error.response?.data || error.message
    );
    throw error;
  }
};
