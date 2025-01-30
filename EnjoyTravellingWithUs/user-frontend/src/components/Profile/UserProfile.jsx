import React, { useEffect, useState } from "react";
import { getUserProfile } from "../../services/profileService";

const UserProfile = () => {
  const [userProfile, setUserProfile] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        const profileData = await getUserProfile();
        setUserProfile(profileData);
      } catch (error) {
        setError("Failed to load profile");
      } finally {
        setIsLoading(false);
      }
    };
    fetchUserProfile();
  }, []);

  if (isLoading) {
    return <p>Loading...</p>;
  }

  return (
    <div>
      <h2>User Profile</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {userProfile ? (
        <div>
          <p>Name: {userProfile.name}</p>
          <p>Email: {userProfile.email}</p>
        </div>
      ) : (
        <p>Failed to load user profile.</p>
      )}
    </div>
  );
};

export default UserProfile;
