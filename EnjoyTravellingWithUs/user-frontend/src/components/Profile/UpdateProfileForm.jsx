// src/components/Profile/UpdateProfileForm.jsx

import React, { useState, useEffect } from "react";
import {
  getUserProfile,
  updateUserProfile,
} from "../../services/profileService";

const UpdateProfileForm = () => {
  const [profileData, setProfileData] = useState({
    name: "",
    email: "",
  });

  useEffect(() => {
    const fetchUserProfile = async () => {
      const profile = await getUserProfile();
      setProfileData(profile);
    };
    fetchUserProfile();
  }, []);

  const handleChange = (e) => {
    setProfileData({
      ...profileData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await updateUserProfile(profileData);
      alert("Profile updated successfully!");
    } catch (error) {
      console.error("Profile Update Error:", error);
    }
  };

  return (
    <div>
      <h2>Update Profile</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder="Name"
          value={profileData.name}
          onChange={handleChange}
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={profileData.email}
          onChange={handleChange}
        />
        <button type="submit">Update</button>
      </form>
    </div>
  );
};

export default UpdateProfileForm;
