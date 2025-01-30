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

function App() {
  return (
    <Router>
      <Navbar />
      <div className="d-flex">
        <Sidebar />
        <div className="container mt-4" style={{ marginLeft: "260px" }}>
          <Routes>
            <Route exact path="/" element={<HomePage />} />
            <Route path="/search" element={<SearchResults />} />
            <Route path="/bus/:busId" element={<BusDetails />} />
            <Route path="/register" element={<RegisterForm />} />
            <Route path="/login" element={<LoginForm />} />
            {/* Update here: Render component inside PrivateRoute */}
            <Route
              path="/profile"
              element={<PrivateRoute element={<UserProfile />} />}
            />
            <Route
              path="/update-profile"
              element={<PrivateRoute element={<UpdateProfileForm />} />}
            />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
