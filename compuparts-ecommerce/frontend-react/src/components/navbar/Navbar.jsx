import React from "react";
import icon from "../../assets/images/navbar-brand-icon.png";

const Navbar = () => {
  return (
    <>
      <nav className="navbar sticky-top navbar-dark bg-dark d-flex justify-content-between align-items-center px-3">
        <div className="d-flex justify-content-between align-items-center">
          <img
            src={icon}
            style={{ height: "40px" }}
            alt="compuparts logo"
            className="me-2"
          />
          <a className="navbar-brand" href="/">
            CompuParts
          </a>
        </div>
        <a className="nav-link text-light" href="/login">
          Login{" "}
        </a>
      </nav>
    </>
  );
};

export default Navbar;
