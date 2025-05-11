// Example in RideBooking.jsx
import React, { useState, useEffect } from "react";
import { Form, Button, Container, Alert, Spinner } from "react-bootstrap";
import { getRideOptions, bookRide as apiBookRide } from "../services/api"; // Assuming you have an api service

const RideBooking = () => {
  // ... (state for pickup, dropoff, vehicleType, error, loading)
  const [pickupLocation, setPickupLocation] = useState("");
  const [dropLocation, setDropLocation] = useState("");
  const [vehicleType, setVehicleType] = useState("");
  const [rideOptions, setRideOptions] = useState([]);
  const [loadingOptions, setLoadingOptions] = useState(false);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  useEffect(() => {
    const fetchOptions = async () => {
      setLoadingOptions(true);
      try {
        const response = await getRideOptions("current_city"); // Replace 'current_city' as needed
        setRideOptions(response.data || []);
        if (response.data && response.data.length > 0) {
          setVehicleType(response.data[0].typeName); // Default to the first option
        }
      } catch (err) {
        setError("Failed to load ride options.");
        console.error(err);
      }
      setLoadingOptions(false);
    };
    fetchOptions();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    setError("");
    setSuccessMessage("");
    try {
      const response = await apiBookRide({
        pickupLocation,
        dropoffLocation,
        vehicleType,
      });
      console.log("Ride booked:", response.data);
      setSuccessMessage(
        `Ride booked successfully! Ride ID: ${response.data.id}. Price: $${response.data.estimatedPrice}`
      );
      // Reset form or redirect
    } catch (err) {
      setError(
        err.response?.data?.message ||
          err.response?.data ||
          "Failed to book ride."
      );
      console.error(err);
    }
    setSubmitting(false);
  };

  return (
    <Container className="mt-5" style={{ maxWidth: "600px" }}>
      <h2>Book a Ride</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      {successMessage && <Alert variant="success">{successMessage}</Alert>}
      <Form onSubmit={handleSubmit}>
        {/* Form Groups for pickup, dropoff */}
        <Form.Group controlId="pickupLocation">
          <Form.Label>Pickup Location</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter pickup address"
            value={pickupLocation}
            onChange={(e) => setPickupLocation(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group controlId="dropLocation" className="mt-3">
          <Form.Label>Drop-off Location</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter drop address"
            value={dropLocation}
            onChange={(e) => setDropLocation(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group controlId="vehicleType" className="mt-3">
          <Form.Label>Select Ride Option</Form.Label>
          {loadingOptions ? (
            <Spinner animation="border" size="sm" />
          ) : (
            <Form.Select
              value={vehicleType}
              onChange={(e) => setVehicleType(e.target.value)}
              required
            >
              <option value="" disabled>
                Select a vehicle
              </option>
              {rideOptions.map((option) => (
                <option key={option.typeName} value={option.typeName}>
                  {option.typeName} ({option.description})
                </option>
              ))}
            </Form.Select>
          )}
        </Form.Group>
        <Button
          variant="primary"
          className="mt-4"
          type="submit"
          disabled={submitting || loadingOptions}
        >
          {submitting ? (
            <Spinner animation="border" size="sm" />
          ) : (
            "Request Ride"
          )}
        </Button>
      </Form>
    </Container>
  );
};

export default RideBooking;
