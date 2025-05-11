// src/pages/RiderLogin.jsx
import React, { useState } from "react";
import { Form, Button, Container, Alert } from "react-bootstrap";
import axios from "axios"; // Import axios
import { useNavigate } from "react-router-dom";

const RiderLogin = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/rider/login", // Your backend API endpoint
        { email, password }
      );

      setLoading(false);
      // Assuming the backend returns a JWT token and user info
      console.log("Login successful:", response.data);
      // Store token in localStorage (or sessionStorage/context)
      localStorage.setItem("instarideUserToken", response.data.token);
      localStorage.setItem("instarideUserInfo", JSON.stringify({
          id: response.data.id,
          name: response.data.name,
          email: response.data.email,
          role: response.data.role
      }));


      // Redirect to a protected route or homepage
      navigate("/book-ride"); // Or wherever you want to go after login

    } catch (err) {
      setLoading(false);
      if (err.response && err.response.data) {
        setError(typeof err.response.data === 'string' ? err.response.data : (err.response.data.message || "Login failed. Please check your credentials."));
      } else {
        setError("Login failed. An unexpected error occurred.");
      }
      console.error("Login error:", err);
    }
  };

  return (
    <Container className="mt-5" style={{ maxWidth: "400px" }}>
      <h2>Rider Login</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <Form onSubmit={handleLogin}>
        <Form.Group controlId="riderEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group controlId="riderPassword" className="mt-3">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </Form.Group>
        <Button variant="primary" className="mt-4" type="submit" disabled={loading}>
          {loading ? "Logging in..." : "Login"}
        </Button>
      </Form>
    </Container>
  );
};

export default RiderLogin;