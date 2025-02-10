import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/wallets";

// Fetch Wallet Balance
export const getWalletByUserId = async (userId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/user/${userId}`);
    return response.data;
  } catch (error) {
    console.error("Wallet Fetch Error:", error.response?.data || error.message);
    throw error;
  }
};

// Add Funds to Wallet
export const addFunds = async (userId, amount) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/add-funds`, null, {
      params: { userId, amount },
    });
    return response.data;
  } catch (error) {
    console.error("Add Funds Error:", error.response?.data || error.message);
    throw error;
  }
};

// Withdraw Funds from Wallet
export const withdrawFunds = async (userId, amount) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/withdraw-funds`, null, {
      params: { userId, amount },
    });
    return response.data;
  } catch (error) {
    console.error(
      "Withdraw Funds Error:",
      error.response?.data || error.message
    );
    throw error;
  }
};
