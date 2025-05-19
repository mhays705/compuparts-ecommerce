import "./App.css";
import axios from "axios";
import React from "react";

import "@fontsource/roboto/300.css";
import "@fontsource/roboto/400.css";
import "@fontsource/roboto/500.css";
import "@fontsource/roboto/700.css";

function App() {
  React.useEffect(() => {
    const fetchData = async () => {
      try {
        const { data } = await axios.get("http://localhost:8080/api/users", {
          auth: {
            username: "admin",
            password: "password",
          },
        });
        console.log(data);
      } catch (error) {
        console.log(error.response);
      }
    };

    fetchData();
  }, []);
  return <>Hello World!</>;
}

export default App;
