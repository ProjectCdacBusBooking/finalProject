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
