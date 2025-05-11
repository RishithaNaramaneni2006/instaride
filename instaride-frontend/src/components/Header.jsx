import React from "react";
import { Navbar, Nav, Container } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";

const Header = () => {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container>
        <LinkContainer to="/">
          <Navbar.Brand>InstaRide</Navbar.Brand>
        </LinkContainer>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse>
          <Nav className="ms-auto">
            <LinkContainer to="/rider-login">
              <Nav.Link>Rider Login</Nav.Link>
            </LinkContainer>
            <LinkContainer to="/rider-signup">
              <Nav.Link>Rider Signup</Nav.Link>
            </LinkContainer>
            <LinkContainer to="/driver-login">
              <Nav.Link>Driver Login</Nav.Link>
            </LinkContainer>
            <LinkContainer to="/driver-signup">
              <Nav.Link>Driver Signup</Nav.Link>
            </LinkContainer>
            <LinkContainer to="/book-ride">
              <Nav.Link>Book Ride</Nav.Link>
            </LinkContainer>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default Header;
