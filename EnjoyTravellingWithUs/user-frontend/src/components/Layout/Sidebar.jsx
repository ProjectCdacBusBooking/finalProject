// src/components/Layout/Sidebar.jsx

import React from "react";
import { Link } from "react-router-dom";

function Sidebar() {
  return (
    <div
      className="d-flex flex-column flex-shrink-0 p-3 bg-light"
      style={{ width: "250px", height: "100vh", position: "fixed" }}
    >
      <h5 className="text-center">Dashboard</h5>
      <hr />
      <ul className="nav nav-pills flex-column mb-auto">
        <li className="nav-item">
          <Link className="nav-link active" to="/profile">
            Profile
          </Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/bookings">
            My Bookings
          </Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/wallet">
            Wallet
          </Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/logout">
            Logout
          </Link>
        </li>
      </ul>
    </div>
  );
}

export default Sidebar;
