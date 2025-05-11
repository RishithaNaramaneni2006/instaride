import React from "react";
import { Form, Button, Container } from "react-bootstrap";

const DriverSignup = () => {
  return (
    <Container className="mt-5" style={{ maxWidth: "400px" }}>
      <h2>Driver Signup</h2>
      <Form>
        <Form.Group controlId="driverName">
          <Form.Label>Full Name</Form.Label>
          <Form.Control type="text" placeholder="Enter name" />
        </Form.Group>
        <Form.Group controlId="driverEmail" className="mt-3">
          <Form.Label>Email</Form.Label>
          <Form.Control type="email" placeholder="Enter email" />
        </Form.Group>
        <Form.Group controlId="driverPhone" className="mt-3">
          <Form.Label>Phone</Form.Label>
          <Form.Control type="text" placeholder="Enter phone number" />
        </Form.Group>
        <Form.Group controlId="driverPassword" className="mt-3">
          <Form.Label>Password</Form.Label>
          <Form.Control type="password" placeholder="Password" />
        </Form.Group>
        <Button variant="success" className="mt-4" type="submit">
          Sign Up
        </Button>
      </Form>
    </Container>
  );
};

export default DriverSignup;
