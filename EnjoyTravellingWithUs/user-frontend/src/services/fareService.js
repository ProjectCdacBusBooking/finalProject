import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/fare";

// Get Fare Between Two Cities
export const getFare = async (source, destination) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/calculate`, {
      params: { source, destination },
    });
    return response.data;
  } catch (error) {
    console.error(
      "Fare Calculation Error:",
      error.response?.data || error.message
    );
    throw error;
  }
};
