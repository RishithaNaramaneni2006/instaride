import React from "react";
import { Form, Button, Container } from "react-bootstrap";

const RiderSignup = () => {
  return (
    <Container className="mt-5" style={{ maxWidth: "400px" }}>
      <h2>Rider Signup</h2>
      <Form>
        <Form.Group controlId="signupName">
          <Form.Label>Full Name</Form.Label>
          <Form.Control type="text" placeholder="Enter name" />
        </Form.Group>
        <Form.Group controlId="signupEmail" className="mt-3">
          <Form.Label>Email</Form.Label>
          <Form.Control type="email" placeholder="Enter email" />
        </Form.Group>
        <Form.Group controlId="signupPhone" className="mt-3">
          <Form.Label>Phone</Form.Label>
          <Form.Control type="text" placeholder="Enter phone number" />
        </Form.Group>
        <Form.Group controlId="signupPassword" className="mt-3">
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

export default RiderSignup;
