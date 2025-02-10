import React, { useEffect, useState } from "react";
import {
  getWalletByUserId,
  addFunds,
  withdrawFunds,
} from "../services/walletService";
import { useNavigate } from "react-router-dom";

const WalletPage = () => {
  const userId = localStorage.getItem("userId");
  const [wallet, setWallet] = useState({ balance: 0 });
  const [amount, setAmount] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetchWallet();
  }, []);

  const fetchWallet = async () => {
    try {
      const data = await getWalletByUserId(userId);
      setWallet(data);
    } catch (error) {
      setError("Error fetching wallet balance.");
    }
  };

  const handleAddFunds = async () => {
    if (!amount || parseFloat(amount) <= 0) {
      setError("Enter a valid amount.");
      return;
    }
    setLoading(true);
    try {
      await addFunds(userId, parseFloat(amount));
      fetchWallet();
      setAmount("");
      setError("");
    } catch (error) {
      setError("Error adding funds.");
    } finally {
      setLoading(false);
    }
  };

  const handleWithdrawFunds = async () => {
    if (parseFloat(amount) > wallet.balance) {
      setError("Insufficient balance.");
      return;
    }
    setLoading(true);
    try {
      await withdrawFunds(userId, parseFloat(amount));
      fetchWallet();
      setAmount("");
      setError("");
    } catch (error) {
      setError("Error withdrawing funds.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="text-center">Wallet</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        <p>Balance: ₹{wallet.balance}</p>
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          placeholder="Enter Amount"
        />
        <button
          className="btn btn-success mt-2"
          onClick={handleAddFunds}
          disabled={loading}
        >
          {loading ? "Processing..." : "Add Funds"}
        </button>
        <button
          className="btn btn-danger mt-2"
          onClick={handleWithdrawFunds}
          disabled={loading}
        >
          {loading ? "Processing..." : "Withdraw Funds"}
        </button>
      </div>
    </div>
  );
};

export default WalletPage;
