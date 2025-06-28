import React from "react";
import { Navbar, Container, Button, Image } from "react-bootstrap";
import icon from "../../assets/images/navbar-brand-icon.png";

const CustomNavbar = ({ loggedIn, setLoggedIn, onLoginClick }) => {
  const handleLogout = () => {
    localStorage.removeItem("token");
    setLoggedIn(false);
  };

  return (
    <Navbar bg="dark" variant="dark" expand="lg" className="px-3">
      <Container fluid className="d-flex justify-content-between">
        <Navbar.Brand href="/" className="d-flex align-items-center">
          <Image
            src={icon}
            alt="CompuParts logo"
            height="40"
            className="me-2"
            rounded
          />
          CompuParts
        </Navbar.Brand>
        {loggedIn ? (
          <Button variant="outline-light" onClick={handleLogout}>
            Logout
          </Button>
        ) : (
          <Button variant="outline-light" onClick={onLoginClick}>
            Login
          </Button>
        )}
      </Container>
    </Navbar>
  );
};

export default CustomNavbar;
