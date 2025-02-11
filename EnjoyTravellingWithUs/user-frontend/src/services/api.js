import axios from "axios";
import { getToken } from "./authService";

const API_BASE_URL = "http://localhost:8080/api";

// ✅ Create an Axios instance with token authorization
const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// ✅ Add an interceptor to include token in every request
api.interceptors.request.use((config) => {
  const token = getToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;
