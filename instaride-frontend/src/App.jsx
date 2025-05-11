// src/App.jsx
import React from "react";
import { Routes, Route } from "react-router-dom";
import AppNavbar from "./components/Header";
import HomePage from "./pages/HomePage";
import RiderLogin from "./pages/RiderLogin";
import RiderSignup from "./pages/RiderSignup";
import DriverLogin from "./pages/DriverLogin";
import DriverSignup from "./pages/DriverSignup";
import BookRide from "./pages/RideBooking";

const App = () => {
  return (
    <>
      <AppNavbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/rider-login" element={<RiderLogin />} />
        <Route path="/rider-signup" element={<RiderSignup />} />
        <Route path="/driver-login" element={<DriverLogin />} />
        <Route path="/driver-signup" element={<DriverSignup />} />
        <Route path="/book-ride" element={<BookRide />} />
      </Routes>
    </>
  );
};

export default App;
