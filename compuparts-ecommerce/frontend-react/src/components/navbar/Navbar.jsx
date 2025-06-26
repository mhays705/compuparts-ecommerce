import React from "react";
import icon from "../../assets/images/navbar-brand-icon.png";

const Navbar = ({ loggedIn, setLoggedIn, onLoginClick }) => {
  const handleLogout = () => {
    localStorage.removeItem("token");
    setLoggedIn(false);
  };

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
        {loggedIn ? (
          <button
            type="button"
            className="btn btn-outline-light"
            onClick={handleLogout}
          >
            Logout
          </button>
        ) : (
          <button
            type="button"
            className="btn btn-outline-light"
            onClick={onLoginClick}
          >
            Login
          </button>
        )}
      </nav>
    </>
  );
};

export default Navbar;
