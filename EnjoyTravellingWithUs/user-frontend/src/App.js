// 📂 user-frontend/src/App.js

import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import RegisterForm from "./components/Auth/RegisterForm";
import LoginForm from "./components/Auth/LoginForm";
import UserProfile from "./components/Profile/UserProfile";
import UpdateProfileForm from "./components/Profile/UpdateProfileForm";
import Navbar from "./components/Layout/Navbar";
import Sidebar from "./components/Layout/Sidebar";
import PrivateRoute from "./utils/PrivateRoute";
import HomePage from "./screens/HomePage";
import SearchResults from "./screens/SearchResults";
import BusDetails from "./screens/BusDetails";
import Booking from "./screens/Booking";
import ConfirmBooking from "./screens/ConfirmBooking";
import BookingSuccess from "./screens/BookingSuccess";
import WalletPage from "./screens/WalletPage";
import BookingHistoryPage from "./screens/BookingHistoryPage";
import BookingCancellationPage from "./screens/BookingCancellationPage";
import NotificationsPage from "./screens/NotificationPage";
import LiveLocationPage from "./screens/LiveLocationPage";
import ETAUpdatesPage from "./screens/ETAUpdatesPage";
import SeatAvailability from "./components/SeatAvailability"; // Added import for SeatAvailability
import "./styles/seatAvailability.css"; // Added import for SeatAvailability CSS

const App = () => {
  const [busId, setBusId] = useState(1); // Example busId
  const [selectedDate, setSelectedDate] = useState("2025-02-01"); // Example date

  return (
    <Router>
      {/* Navbar ha top section la render honar */}
      <Navbar />
      <div className="d-flex">
        {/* Sidebar ha left side la render honar */}
        <Sidebar />
        <div className="container mt-4" style={{ marginLeft: "260px" }}>
          <Routes>
            {/* Home Page */}
            <Route exact path="/" element={<HomePage />} />

            {/* Search results page */}
            <Route path="/search" element={<SearchResults />} />

            {/* Bus details page */}
            <Route path="/bus/:busId" element={<BusDetails />} />

            <Route path="/book/:busId" element={<Booking />} />

            <Route
              path="/confirm-booking/:bookingId"
              element={<ConfirmBooking />}
            />

            <Route
              path="/booking-success/:bookingId"
              element={<BookingSuccess />}
            />

            {/* Registration form */}
            <Route path="/register" element={<RegisterForm />} />

            <Route path="/wallet" element={<WalletPage />} />

            <Route path="/booking-history" element={<BookingHistoryPage />} />

            <Route
              path="/buses/:busId/live-location"
              element={<LiveLocationPage />}
            />

            <Route path="/buses/:busId/eta" element={<ETAUpdatesPage />} />

            <Route
              path="/notifications/:userId"
              element={<NotificationsPage />}
            />

            <Route
              path="/cancel-booking/:bookingId"
              element={<BookingCancellationPage />}
            />

            {/* Login form */}
            <Route path="/login" element={<LoginForm />} />

            {/* PrivateRoute wrap kela aahe authentication sathi */}
            <Route
              path="/profile"
              element={<PrivateRoute element={<UserProfile />} />}
            />
            <Route
              path="/update-profile"
              element={<PrivateRoute element={<UpdateProfileForm />} />}
            />

            {/* Seat Availability component */}
            <Route
              path="/seat-availability"
              element={
                <div>
                  <h1>Bus Booking System</h1>
                  {/* Date and bus selection (for the sake of this example, we are hardcoding these values) */}
                  <div>
                    <label htmlFor="busId">Select Bus:</label>
                    <select
                      id="busId"
                      value={busId}
                      onChange={(e) => setBusId(e.target.value)}
                    >
                      <option value={1}>Bus 1</option>
                      <option value={2}>Bus 2</option>
                      <option value={3}>Bus 3</option>
                    </select>
                  </div>

                  <div>
                    <label htmlFor="date">Select Date:</label>
                    <input
                      type="date"
                      id="date"
                      value={selectedDate}
                      onChange={(e) => setSelectedDate(e.target.value)}
                    />
                  </div>

                  {/* Display seat availability */}
                  <SeatAvailability busId={busId} selectedDate={selectedDate} />
                </div>
              }
            />
          </Routes>
        </div>
      </div>
    </Router>
  );
};

export default App;
