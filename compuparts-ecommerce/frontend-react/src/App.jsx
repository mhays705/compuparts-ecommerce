import "./App.css";

import React from "react";

import "@fontsource/roboto/300.css";
import "@fontsource/roboto/400.css";
import "@fontsource/roboto/500.css";
import "@fontsource/roboto/700.css";
import "bootstrap/dist/css/bootstrap.min.css";
import CustomNavbar from "./components/customNavbar/CustomNavbar";
import LoginModal from "./components/modals/loginModal/LoginModal";
import RegisterCustomerModal from "./components/modals/registerCustomerModal/RegisterCustomerModal";
import Hero from "./components/hero/Hero";
import Footer from "./components/footer/Footer";
import CustomerDashboard from "./components/customerDashboard/customerDashboard";
import { useState } from "react";

function App() {
  const [loggedIn, setLoggedIn] = useState(() => {
    return !!localStorage.getItem("token");
  });
  const [showLogin, setShowLogin] = useState(false);
  const [showRegister, setShowRegister] = useState(false);

  return (
    <>
      <CustomNavbar
        loggedIn={loggedIn}
        setLoggedIn={setLoggedIn}
        onLoginClick={() => setShowLogin(true)}
      />
      <LoginModal
        showLogin={showLogin}
        onHide={() => setShowLogin(false)}
        setLoggedIn={setLoggedIn}
        setShowRegister={setShowRegister}
      />
      <RegisterCustomerModal
        showRegister={showRegister}
        onHide={() => setShowRegister(false)}
        setShowLogin={setShowLogin}
      />
      {/* <CustomerDashboard /> */}
      <Hero setShowRegister={setShowRegister} />
      <Footer />
    </>
  );
}

export default App;
