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
import SeatAvailability from "./components/SeatAvailability";
import SeatSelection from "./components/SeatSelection";
import BookingHistory from "./components/BookingHistory";
import PushNotification from "./components/PushNotification";
import RatingReview from "./components/RatingReview";

import "bootstrap/dist/css/bootstrap.min.css"; // Import Bootstrap
import "./styles/seatAvailability.css";
import "./styles/seatSelection.css";
import "./styles/bookingHistory.css";
import "./styles/pushNotification.css";

const App = () => {
  const [busId, setBusId] = useState(1);
  const [selectedDate, setSelectedDate] = useState("2025-02-01");
  const [availableSeats, setAvailableSeats] = useState([]);
  const [userId, setUserId] = useState(1); // Mock userId
  const [selectedBusId, setSelectedBusId] = useState(101); // Mock BusId for Rating & Review

  // Function to handle seat availability update
  const handleSeatAvailability = (seats) => {
    setAvailableSeats(seats);
  };

  return (
    <Router>
      <Navbar />
      <div className="d-flex">
        <Sidebar />
        <div className="container mt-4" style={{ marginLeft: "260px" }}>
          <Routes>
            {/* Main Routes */}
            <Route exact path="/" element={<HomePage />} />
            <Route path="/search" element={<SearchResults />} />
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
            <Route path="/wallet" element={<WalletPage />} />
            <Route path="/booking-history" element={<BookingHistoryPage />} />
            <Route
              path="/cancel-booking/:bookingId"
              element={<BookingCancellationPage />}
            />
            <Route
              path="/buses/:busId/live-location"
              element={<LiveLocationPage />}
            />
            <Route path="/buses/:busId/eta" element={<ETAUpdatesPage />} />
            <Route
              path="/notifications/:userId"
              element={<NotificationsPage />}
            />

            {/* Authentication Routes */}
            <Route path="/register" element={<RegisterForm />} />
            <Route path="/login" element={<LoginForm />} />

            {/* Private Routes */}
            <Route
              path="/profile"
              element={<PrivateRoute element={<UserProfile />} />}
            />
            <Route
              path="/update-profile"
              element={<PrivateRoute element={<UpdateProfileForm />} />}
            />

            {/* Seat Availability & Selection */}
            <Route
              path="/seat-availability"
              element={
                <div className="text-center">
                  <h1 className="mb-4">Bus Booking System</h1>
                  <div className="mb-3">
                    <label htmlFor="busId" className="form-label">
                      Select Bus:
                    </label>
                    <select
                      id="busId"
                      className="form-select"
                      value={busId}
                      onChange={(e) => setBusId(Number(e.target.value))}
                    >
                      <option value={1}>Bus 1</option>
                      <option value={2}>Bus 2</option>
                      <option value={3}>Bus 3</option>
                    </select>
                  </div>

                  <div className="mb-3">
                    <label htmlFor="date" className="form-label">
                      Select Date:
                    </label>
                    <input
                      type="date"
                      id="date"
                      className="form-control"
                      value={selectedDate}
                      onChange={(e) => setSelectedDate(e.target.value)}
                    />
                  </div>

                  {/* Seat Availability Check */}
                  <SeatAvailability
                    busId={busId}
                    selectedDate={selectedDate}
                    onSeatsFetched={handleSeatAvailability}
                  />

                  {/* Seat Selection */}
                  <SeatSelection
                    busId={busId}
                    selectedDate={selectedDate}
                    availableSeats={availableSeats}
                  />
                </div>
              }
            />

            {/* Booking History */}
            <Route
              path="/user-booking-history"
              element={<BookingHistory userId={userId} />}
            />

            {/* Push Notification */}
            <Route path="/push-notifications" element={<PushNotification />} />

            {/* Rating & Review */}
            <Route
              path="/rate-bus"
              element={<RatingReview busId={selectedBusId} userId={userId} />}
            />
          </Routes>
        </div>
      </div>
    </Router>
  );
};

export default App;
