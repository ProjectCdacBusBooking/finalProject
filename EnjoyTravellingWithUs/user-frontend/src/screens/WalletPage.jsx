// 📂 src/screens/WalletPage.jsx

import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom"; // useNavigate instead of useHistory
import { motion } from "framer-motion"; // Added framer-motion for animation

function WalletPage() {
  const [balance, setBalance] = useState(0);
  const [amount, setAmount] = useState("");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate(); // Initialize useNavigate

  useEffect(() => {
    // Fetch wallet balance
    const fetchWalletBalance = async () => {
      try {
        const userId = localStorage.getItem("userId");
        const response = await axios.get(
          `http://localhost:8080/wallet/${userId}`
        );
        setBalance(response.data.balance);
      } catch (err) {
        setError("Error fetching wallet balance. Please try again.");
      }
    };

    fetchWalletBalance();
  }, []);

  const handleAddMoney = async () => {
    if (parseFloat(amount) <= 0) {
      setError("Please enter a valid amount.");
      return;
    }
    setIsLoading(true);
    try {
      const userId = localStorage.getItem("userId");
      await axios.post(`http://localhost:8080/wallet/add-money/${userId}`, {
        amount,
      });
      setBalance((prevBalance) => prevBalance + parseFloat(amount)); // Smooth transition of balance
      setAmount(""); // Clear amount field
      setError(""); // Clear error message
    } catch (err) {
      setError("Error adding money to wallet. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  const handleMakePayment = async (amountToPay) => {
    if (amountToPay > balance) {
      setError("Insufficient balance to make the payment.");
      return;
    }

    // Confirmation before making payment
    if (window.confirm(`Are you sure you want to pay ₹${amountToPay}?`)) {
      setIsLoading(true);
      try {
        const userId = localStorage.getItem("userId");
        await axios.post(`http://localhost:8080/wallet/pay`, {
          userId,
          amount: amountToPay,
        });
        setBalance((prevBalance) => prevBalance - parseFloat(amountToPay));
        navigate("/booking-success"); // Replaced history.push with navigate
      } catch (err) {
        setError("Error making payment. Please try again.");
      } finally {
        setIsLoading(false);
      }
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <motion.h2
          className="text-center"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ duration: 1 }}
        >
          Wallet Management
        </motion.h2>

        {error && <div className="alert alert-danger">{error}</div>}

        <motion.div
          className="wallet-info"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ duration: 1, delay: 0.5 }}
        >
          <h4>Wallet Balance: ₹{balance}</h4>
          <div className="mb-3">
            <input
              type="number"
              className="form-control"
              placeholder="Amount to Add"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
            />
            <button
              className="btn btn-success w-100 mt-3"
              onClick={handleAddMoney}
              disabled={isLoading || parseFloat(amount) <= 0}
            >
              {isLoading ? "Adding..." : "Add Money to Wallet"}
            </button>
          </div>
          <motion.button
            className="btn btn-primary w-100 mt-3"
            onClick={() => handleMakePayment(balance)}
            disabled={isLoading || balance <= 0}
            whileHover={{ scale: 1.05 }} // Hover effect
            transition={{ duration: 0.3 }}
          >
            {isLoading ? "Processing Payment..." : "Make Payment"}
          </motion.button>
        </motion.div>
      </div>
    </div>
  );
}

export default WalletPage;
