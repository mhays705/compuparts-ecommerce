import "./App.css";

import React from "react";

import "@fontsource/roboto/300.css";
import "@fontsource/roboto/400.css";
import "@fontsource/roboto/500.css";
import "@fontsource/roboto/700.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./components/navbar/NavBar";
import LoginModal from "./components/modals/LoginModal";
import { useState } from "react";

function App() {
  const [loggedIn, setLoggedIn] = useState(() => {
    return !!localStorage.getItem("token");
  });
  const [showLogin, setShowLogin] = useState(false);

  return (
    <>
      <Navbar
        loggedIn={loggedIn}
        setLoggedIn={setLoggedIn}
        onLoginClick={() => setShowLogin(true)}
      />
      <LoginModal
        show={showLogin}
        onHide={() => setShowLogin(false)}
        setLoggedIn={setLoggedIn}
      />
    </>
  );
}

export default App;
