import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/wallets";

// Function to get the userId from localStorage
const getUserId = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  return user ? user.id : null;
};

// Fetch Wallet Details
export const getWalletByUserId = async () => {
  const userId = getUserId();
  if (!userId) {
    console.error("❌ User ID not found. Cannot fetch wallet.");
    return { error: "User ID is missing." };
  }

  try {
    const response = await axios.get(`${API_BASE_URL}/user/${userId}`);
    return response.data;
  } catch (error) {
    console.error(
      "❌ Wallet Fetch Error:",
      error.response?.data || error.message
    );
    return {
      error:
        error.response?.data || "An error occurred while fetching the wallet.",
    };
  }
};

// Update Wallet Balance
export const updateWalletBalance = async (amount) => {
  const userId = getUserId();
  if (!userId) {
    console.error("❌ User ID not found. Cannot update wallet.");
    return { error: "User ID is missing." };
  }

  try {
    const response = await axios.put(`${API_BASE_URL}/user/${userId}/update`, {
      amount,
    });
    return response.data;
  } catch (error) {
    console.error(
      "❌ Wallet Update Error:",
      error.response?.data || error.message
    );
    return {
      error:
        error.response?.data || "An error occurred while updating the wallet.",
    };
  }
};
