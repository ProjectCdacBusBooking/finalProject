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

function App() {
  return (
    <Router>
      <Navbar />
      <div className="d-flex">
        <Sidebar />
        <div className="container mt-4" style={{ marginLeft: "260px" }}>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/register" element={<RegisterForm />} />
            <Route path="/login" element={<LoginForm />} />
            <Route
              path="/profile"
              element={<PrivateRoute component={UserProfile} />}
            />
            <Route
              path="/update-profile"
              element={<PrivateRoute component={UpdateProfileForm} />}
            />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
