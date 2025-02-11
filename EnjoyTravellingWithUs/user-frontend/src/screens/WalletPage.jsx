import React, { useEffect, useState } from "react";
import axios from "axios";

const WalletPage = () => {
  const [walletBalance, setWalletBalance] = useState(0); // ✅ Fix: Declare state

  const fetchWallet = async () => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user || !user.id) {
      console.error("❌ User ID not found. Cannot fetch wallet.");
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8080/api/wallets/user/${user.id}`
      );
      setWalletBalance(response.data.balance); // ✅ Fix: Ensure this state is defined
    } catch (error) {
      console.error("❌ Error fetching wallet:", error);
    }
  };

  useEffect(() => {
    fetchWallet();
  }, []);

  return (
    <div className="container">
      <h2>💰 Wallet Balance</h2>
      <p>Your balance: ₹{walletBalance}</p>
    </div>
  );
};

export default WalletPage;
