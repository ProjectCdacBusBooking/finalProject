// // 📂 src/screens/WalletPage.jsx

// import React, { useState, useEffect } from "react";
// import axios from "axios";
// import { useNavigate } from "react-router-dom"; // useNavigate instead of useHistory
// import { motion } from "framer-motion"; // Added framer-motion for animation

// function WalletPage() {
//   const [balance, setBalance] = useState(0);
//   const [amount, setAmount] = useState("");
//   const [error, setError] = useState("");
//   const [isLoading, setIsLoading] = useState(false);
//   const navigate = useNavigate(); // Initialize useNavigate

//   useEffect(() => {
//     // Fetch wallet balance
//     const fetchWalletBalance = async () => {
//       try {
//         const userId = localStorage.getItem("userId");
//         const response = await axios.get(
//           `http://localhost:8080/wallet/${userId}`
//         );
//         setBalance(response.data.balance);
//       } catch (err) {
//         setError("Error fetching wallet balance. Please try again.");
//       }
//     };

//     fetchWalletBalance();
//   }, []);

//   const handleAddMoney = async () => {
//     if (parseFloat(amount) <= 0) {
//       setError("Please enter a valid amount.");
//       return;
//     }
//     setIsLoading(true);
//     try {
//       const userId = localStorage.getItem("userId");
//       await axios.post(`http://localhost:8080/wallet/add-money/${userId}`, {
//         amount,
//       });
//       setBalance((prevBalance) => prevBalance + parseFloat(amount)); // Smooth transition of balance
//       setAmount(""); // Clear amount field
//       setError(""); // Clear error message
//     } catch (err) {
//       setError("Error adding money to wallet. Please try again.");
//     } finally {
//       setIsLoading(false);
//     }
//   };

//   const handleMakePayment = async (amountToPay) => {
//     if (amountToPay > balance) {
//       setError("Insufficient balance to make the payment.");
//       return;
//     }

//     // Confirmation before making payment
//     if (window.confirm(`Are you sure you want to pay ₹${amountToPay}?`)) {
//       setIsLoading(true);
//       try {
//         const userId = localStorage.getItem("userId");
//         await axios.post(`http://localhost:8080/wallet/pay`, {
//           userId,
//           amount: amountToPay,
//         });
//         setBalance((prevBalance) => prevBalance - parseFloat(amountToPay));
//         navigate("/booking-success"); // Replaced history.push with navigate
//       } catch (err) {
//         setError("Error making payment. Please try again.");
//       } finally {
//         setIsLoading(false);
//       }
//     }
//   };

//   return (
//     <div className="container mt-4">
//       <div className="card shadow p-4">
//         <motion.h2
//           className="text-center"
//           initial={{ opacity: 0 }}
//           animate={{ opacity: 1 }}
//           transition={{ duration: 1 }}
//         >
//           Wallet Management
//         </motion.h2>

//         {error && <div className="alert alert-danger">{error}</div>}

//         <motion.div
//           className="wallet-info"
//           initial={{ opacity: 0 }}
//           animate={{ opacity: 1 }}
//           transition={{ duration: 1, delay: 0.5 }}
//         >
//           <h4>Wallet Balance: ₹{balance}</h4>
//           <div className="mb-3">
//             <input
//               type="number"
//               className="form-control"
//               placeholder="Amount to Add"
//               value={amount}
//               onChange={(e) => setAmount(e.target.value)}
//             />
//             <button
//               className="btn btn-success w-100 mt-3"
//               onClick={handleAddMoney}
//               disabled={isLoading || parseFloat(amount) <= 0}
//             >
//               {isLoading ? "Adding..." : "Add Money to Wallet"}
//             </button>
//           </div>
//           <motion.button
//             className="btn btn-primary w-100 mt-3"
//             onClick={() => handleMakePayment(balance)}
//             disabled={isLoading || balance <= 0}
//             whileHover={{ scale: 1.05 }} // Hover effect
//             transition={{ duration: 0.3 }}
//           >
//             {isLoading ? "Processing Payment..." : "Make Payment"}
//           </motion.button>
//         </motion.div>
//       </div>
//     </div>
//   );
// }

// export default WalletPage;

// 📂 src/screens/WalletPage.jsx

/*
import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function WalletPage() {
  const [wallet, setWallet] = useState({});
  const [transactionHistory, setTransactionHistory] = useState([]);
  const [amount, setAmount] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const navigate = useNavigate(); // useNavigate instead of useHistory

  useEffect(() => {
    // Fetch wallet details and transaction history
    const fetchWalletDetails = async () => {
      try {
        const userId = localStorage.getItem("userId");

        if (!userId) {
          setError("User not logged in. Please log in to view wallet.");
          navigate("/login"); // Redirect to login if no user ID
          return;
        }

        const response = await axios.get(
          `http://localhost:8080/wallet/${userId}`
        );
        setWallet(response.data);

        const transactionsResponse = await axios.get(
          `http://localhost:8080/wallet/transactions/${userId}`
        );
        setTransactionHistory(transactionsResponse.data);
      } catch (err) {
        setError("Error fetching wallet details. Please try again.");
      }
    };

    fetchWalletDetails();
  }, [navigate]); // Adding navigate in the dependency array

  const handleAddMoney = async () => {
    try {
      const userId = localStorage.getItem("userId");

      if (!userId) {
        setError("User not logged in. Please log in to add money.");
        navigate("/login"); // Redirect to login if no user ID
        return;
      }

      await axios.post(`http://localhost:8080/wallet/add-money/${userId}`, {
        amount,
      });
      setSuccess("Money added successfully to your wallet.");
      setAmount("");
      // Reload wallet details after adding money
      setTimeout(() => {
        window.location.reload();
      }, 2000);
    } catch (err) {
      setError("Error adding money. Please try again.");
    }
  };

  const handlePayment = async (amount) => {
    try {
      const userId = localStorage.getItem("userId");

      if (!userId) {
        setError("User not logged in. Please log in to make a payment.");
        navigate("/login"); // Redirect to login if no user ID
        return;
      }

      await axios.post(`http://localhost:8080/wallet/pay`, { userId, amount });
      setSuccess("Payment successful.");
      // Reload wallet details after payment
      setTimeout(() => {
        window.location.reload();
      }, 2000);
    } catch (err) {
      setError("Error making payment. Please try again.");
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="text-center">Wallet Management</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        {success && <div className="alert alert-success">{success}</div>}
        <div>
          <h5>Wallet Balance: ₹{wallet.balance}</h5>
          <button
            className="btn btn-primary"
            onClick={() => handlePayment(wallet.balance)}
          >
            Make Payment
          </button>
        </div>
        <hr />
        <h4>Add Money to Wallet</h4>
        <input
          type="number"
          className="form-control"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          placeholder="Enter amount"
          min="1" // You can set a minimum limit for money input
        />
        <button className="btn btn-success mt-3" onClick={handleAddMoney}>
          Add Money
        </button>
        <hr />
        <h4>Transaction History</h4>
        <ul className="list-group">
          {transactionHistory.map((transaction, index) => (
            <li key={index} className="list-group-item">
              {transaction.date} - ₹{transaction.amount} - {transaction.type}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default WalletPage;

*/

import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom"; // Import useParams hook

function WalletPage() {
  const [balance, setBalance] = useState(0);
  const [transactionHistory, setTransactionHistory] = useState([]);
  const [amount, setAmount] = useState("");
  const [error, setError] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(true);

  // Use useParams to get the userId from the route
  const { userId } = useParams(); // Destructure the userId from useParams

  useEffect(() => {
    // Fetch wallet balance and transaction history
    const fetchWalletData = async () => {
      setLoading(true); // Start loading
      try {
        const balanceResponse = await axios.get(
          `http://localhost:8080/wallet/${userId}`
        );
        const transactionResponse = await axios.get(
          `http://localhost:8080/wallet/transactions/${userId}`
        );
        setBalance(balanceResponse.data);
        setTransactionHistory(transactionResponse.data);
      } catch (err) {
        setError("Error fetching wallet data.");
      }
      setLoading(false); // End loading
    };

    fetchWalletData();
  }, [userId]);

  const addMoney = async () => {
    if (amount <= 0) {
      setError("Amount must be a positive number.");
      return;
    }

    try {
      const response = await axios.post(
        `http://localhost:8080/wallet/add-money/${userId}?amount=${amount}`
      );
      setMessage("Money added successfully!");
      setBalance(response.data); // Assuming it returns the updated balance
      setAmount(""); // Clear the input
    } catch (err) {
      setError("Error adding money.");
    }
  };

  const makePayment = async () => {
    if (amount <= 0) {
      setError("Amount must be a positive number.");
      return;
    }

    try {
      const response = await axios.post(
        `http://localhost:8080/wallet/pay?userId=${userId}&amount=${amount}`
      );
      setMessage("Payment successful!");
      setBalance(response.data); // Assuming it returns the updated balance
      setAmount(""); // Clear the input
    } catch (err) {
      setError("Error making payment.");
    }
  };

  // Hide success or error messages after 3 seconds
  useEffect(() => {
    if (message || error) {
      setTimeout(() => {
        setMessage("");
        setError("");
      }, 3000);
    }
  }, [message, error]);

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="text-center">Wallet Management</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        {message && <div className="alert alert-success">{message}</div>}
        <div>
          {loading ? (
            <div>Loading wallet data...</div>
          ) : (
            <>
              <h4>Wallet Balance: ₹{balance}</h4>
              <div className="mb-3">
                <input
                  type="number"
                  className="form-control"
                  value={amount}
                  onChange={(e) => setAmount(e.target.value)}
                  placeholder="Enter amount"
                />
              </div>
              <button className="btn btn-primary" onClick={addMoney}>
                Add Money
              </button>
              <button className="btn btn-success ml-3" onClick={makePayment}>
                Make Payment
              </button>
              <h4 className="mt-4">Transaction History</h4>
              {transactionHistory.length === 0 ? (
                <p>No transactions available.</p>
              ) : (
                <ul className="list-group">
                  {transactionHistory.map((transaction, index) => (
                    <li key={index} className="list-group-item">
                      {transaction.date}: ₹{transaction.amount} -{" "}
                      {transaction.type}
                    </li>
                  ))}
                </ul>
              )}
            </>
          )}
        </div>
      </div>
    </div>
  );
}

export default WalletPage;
