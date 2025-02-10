import React, { useState } from "react";
import axios from "axios";

const RatingReview = ({ busId, userId }) => {
  const [rating, setRating] = useState(1);
  const [review, setReview] = useState("");

  const handleSubmit = async () => {
    const payload = {
      userId,
      busId,
      rating,
      review,
    };

    try {
      await axios.post("/api/ratings-reviews", payload);
      alert("Review submitted successfully!");
    } catch (error) {
      alert("Error submitting review. Please try again.");
    }
  };

  return (
    <div>
      <h3>Rate and Review Bus</h3>
      <div>
        <label>Rating: </label>
        <select value={rating} onChange={(e) => setRating(e.target.value)}>
          {[1, 2, 3, 4, 5].map((star) => (
            <option key={star} value={star}>
              {star}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Review: </label>
        <textarea
          value={review}
          onChange={(e) => setReview(e.target.value)}
        ></textarea>
      </div>
      <button onClick={handleSubmit}>Submit Review</button>
    </div>
  );
};

export default RatingReview;

// import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/reviews";

// Add Review
export const addReview = async (reviewData) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/add`, reviewData);
    return response.data;
  } catch (error) {
    console.error("Add Review Error:", error.response?.data || error.message);
    throw error;
  }
};

// Get Reviews by Bus ID
export const getReviewsByBus = async (busId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/bus/${busId}`);
    return response.data;
  } catch (error) {
    console.error(
      "Fetch Reviews Error:",
      error.response?.data || error.message
    );
    throw error;
  }
};

// Get Average Rating
export const getAverageRating = async (busId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/average/${busId}`);
    return response.data;
  } catch (error) {
    console.error(
      "Fetch Average Rating Error:",
      error.response?.data || error.message
    );
    throw error;
  }
};
