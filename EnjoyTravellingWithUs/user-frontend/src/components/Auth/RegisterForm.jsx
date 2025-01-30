import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../../services/authService";

const RegisterForm = () => {
  const [formData, setFormData] = useState({
    fullName: "", // ✅ Correct field name (was 'name')
    email: "",
    password: "",
    phone: "",
  });

  const [error, setError] = useState(""); // Error state
  const navigate = useNavigate(); // ✅ Corrected variable name (was 'history')

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(""); // Reset errors

    try {
      await registerUser(formData);
      navigate("/login"); // ✅ Corrected variable name (was 'history.push')
    } catch (error) {
      console.error("Registration Error:", error);
      setError("Registration failed. Please check your details.");
    }
  };

  return (
    <div>
      <h2>Register</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}{" "}
      {/* ✅ Display error */}
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="fullName" // ✅ Changed from 'name' to 'fullName'
          placeholder="Full Name"
          value={formData.fullName}
          onChange={handleChange}
          required
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
          required
          minLength={6} // ✅ Ensure at least 6 characters
        />
        <input
          type="tel"
          name="phone"
          placeholder="Phone" // ✅ Corrected placeholder (was 'phone')
          value={formData.phone}
          onChange={handleChange}
          required
        />
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default RegisterForm;
