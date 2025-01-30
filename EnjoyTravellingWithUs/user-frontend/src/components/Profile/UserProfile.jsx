// src/components/Profile/UserProfile.jsx

import React, { useEffect, useState } from "react";
import { getUserProfile } from "../../services/profileService";

const UserProfile = () => {
  const [userProfile, setUserProfile] = useState(null);

  useEffect(() => {
    const fetchUserProfile = async () => {
      const profileData = await getUserProfile();
      setUserProfile(profileData);
    };
    fetchUserProfile();
  }, []);

  return (
    <div>
      <h2>User Profile</h2>
      {userProfile ? (
        <div>
          <p>Name: {userProfile.name}</p>
          <p>Email: {userProfile.email}</p>
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};

export default UserProfile;
