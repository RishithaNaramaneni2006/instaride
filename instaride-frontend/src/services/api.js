// src/services/api.js
import axios from "axios";

const API_URL = "http://localhost:8080/api"; // Your base API URL

const apiClient = axios.create({
  baseURL: API_URL,
});

apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("instarideUserToken");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Auth services
export const loginRider = (credentials) =>
  apiClient.post("/auth/rider/login", credentials);
export const signupRider = (userData) =>
  apiClient.post("/auth/rider/signup", userData);
// ... other auth services for driver

// Ride services
export const bookRide = (rideData) => apiClient.post("/rides/book", rideData);
export const getRideOptions = (area) =>
  apiClient.get(`/rides/options?area=${area}`);
export const getRiderHistory = () => apiClient.get("/rides/history/rider");

// Account services
export const getRiderProfile = () => apiClient.get("/account/rider");
export const updateRiderProfile = (profileData) =>
  apiClient.put("/account/rider", profileData);

export default apiClient;
