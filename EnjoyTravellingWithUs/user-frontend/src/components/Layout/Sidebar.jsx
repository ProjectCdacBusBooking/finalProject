// src/components/Layout/Sidebar.jsx

import React from "react";
import { Link } from "react-router-dom";
import { FaUser, FaWallet, FaBus, FaSignOutAlt } from "react-icons/fa";

function Sidebar() {
  return (
    <div
      className="d-flex flex-column flex-shrink-0 p-4 bg-light shadow-lg"
      style={{
        width: "250px",
        height: "100vh",
        position: "fixed",
        top: "56px",
        borderRadius: "10px",
        transition: "all 0.3s ease-in-out",
      }}
    >
      {/* Sidebar Heading */}
      <h5 className="text-center text-primary mb-4 fw-bold">Dashboard</h5>
      <hr />

      {/* Navigation Links */}
      <ul className="nav flex-column">
        <li className="nav-item">
          <Link
            className="nav-link d-flex align-items-center text-dark fw-bold"
            to="/profile"
            style={{ transition: "color 0.3s ease" }}
          >
            <FaUser className="me-3" style={{ fontSize: "1.5rem" }} />
            Profile
          </Link>
        </li>
        <li className="nav-item">
          <Link
            className="nav-link d-flex align-items-center text-dark fw-bold"
            to="/bookings"
            style={{ transition: "color 0.3s ease" }}
          >
            <FaBus className="me-3" style={{ fontSize: "1.5rem" }} />
            My Bookings
          </Link>
        </li>
        <li className="nav-item">
          <Link
            className="nav-link d-flex align-items-center text-dark fw-bold"
            to="/wallet"
            style={{ transition: "color 0.3s ease" }}
          >
            <FaWallet className="me-3" style={{ fontSize: "1.5rem" }} />
            Wallet
          </Link>
        </li>
        <li className="nav-item">
          <Link
            className="nav-link d-flex align-items-center text-danger fw-bold"
            to="/logout"
            style={{ transition: "color 0.3s ease" }}
          >
            <FaSignOutAlt className="me-3" style={{ fontSize: "1.5rem" }} />
            Logout
          </Link>
        </li>
      </ul>

      {/* Hover Effects */}
      <style jsx>{`
        .nav-link:hover {
          color: #0056b3;
          background-color: #e6f7ff;
          border-radius: 5px;
          transform: scale(1.05);
        }
        .nav-item.active .nav-link {
          color: #ffffff;
          background-color: #007bff;
        }
      `}</style>
    </div>
  );
}

export default Sidebar;
