// src/App.js

import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Routes,
} from "react-router-dom";
import RegisterForm from "./components/Auth/RegisterForm";
import LoginForm from "./components/Auth/LoginForm";
import UserProfile from "./components/Profile/UserProfile";
import UpdateProfileForm from "./components/Profile/UpdateProfileForm";
import Navbar from "./components/Layout/Navbar";
import Sidebar from "./components/Layout/Sidebar";
import PrivateRoute from "./utils/PrivateRoute";

function App() {
  return (
    <Router>
      <Navbar />
      <div className="d-flex">
        <Sidebar />
        <div className="container mt-4" style={{ marginLeft: "260px" }}>
          <Routes>
            <Route path="/register" component={RegisterForm} />
            <Route path="/login" component={LoginForm} />
            <PrivateRoute path="/profile" component={UserProfile} />
            <PrivateRoute
              path="/update-profile"
              component={UpdateProfileForm}
            />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
