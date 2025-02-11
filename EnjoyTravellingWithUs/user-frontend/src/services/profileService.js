import axios from "axios";

const API_URL = "http://localhost:8080/api/users";

// Fetch User Profile
export const getUserProfile = async () => {
  const user = JSON.parse(localStorage.getItem("user"));
  if (!user || !user.id) {
    console.error("❌ User ID not found in localStorage.");
    return null;
  }

  try {
    const response = await axios.get(`${API_URL}/${user.id}`);
    return response.data;
  } catch (error) {
    console.error(
      "❌ Profile Fetch Error:",
      error.response?.data || error.message
    );
    throw error;
  }
};
// Update User Profile
export const updateUserProfile = async (data) => {
  try {
    const token = localStorage.getItem("token");

    if (!token) throw new Error("User not authenticated");

    const response = await axios.put(`${API_URL}/update`, data, {
      headers: { Authorization: `Bearer ${token}` },
    });

    return response.data;
  } catch (error) {
    console.error(
      "Profile Update Error:",
      error.response?.data || error.message
    );
    throw error;
  }
};
