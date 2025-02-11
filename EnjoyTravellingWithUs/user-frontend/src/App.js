import React from "react";
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
import RatingReview from "./components/RatingReview";
import PushNotification from "./components/PushNotification";

import "bootstrap/dist/css/bootstrap.min.css"; // ✅ Ensure Bootstrap is loaded
import "./styles/global.css"; // ✅ Ensure global styles exist
import SeatAvailabilityPage from "./components/SeatAvailabilityPage";

const App = () => {
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
            <Route path="/booking" element={<Booking />} /> {/* ✅ Added */}
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
            <Route path="/notifications" element={<NotificationsPage />} />{" "}
            {/* ✅ Fixed Route */}
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
            {/* Seat Availability & Booking */}
            <Route
              path="/seat-availability"
              element={<SeatAvailabilityPage />}
            />
            {/* Push Notifications */}
            <Route path="/push-notifications" element={<PushNotification />} />
            {/* Rating & Review */}
            <Route path="/rate-bus" element={<RatingReview />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
};

export default App;
