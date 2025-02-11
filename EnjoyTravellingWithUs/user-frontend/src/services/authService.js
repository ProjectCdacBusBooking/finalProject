import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/users";

// ✅ Add registerUser function
export const registerUser = async (userData) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/register`, userData);
    return response.data;
  } catch (error) {
    console.error(
      "❌ Registration Error:",
      error.response?.data || error.message
    );
    throw error;
  }
};

// ✅ Fix: Ensure loginUser is correctly exported
export const loginUser = async (email, password) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/login`, {
      email,
      password,
    });

    if (response.data) {
      localStorage.setItem("user", JSON.stringify(response.data)); // ✅ Store user details
    }

    return response.data;
  } catch (error) {
    console.error("❌ Login Error:", error.response?.data || error.message);
    return { error: error.response?.data || "Login failed. Please try again." };
  }
};

export const getToken = () => localStorage.getItem("token");

export const logoutUser = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("user");
};
