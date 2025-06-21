import axios from "axios";
import { useState } from "react";
// import { useNavigate } from "react-router-dom";

const LoginForm = () => {
  const url = "http://localhost:8080/api/users/login";
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  // const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const res = await axios.post(url, { username, password });
      localStorage.setItem("token", res.data.token);
      //navigate("/dashboard");
    } catch (error) {
      setError("Invalid username or password");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            placeholder="Please enter username"
            id="username"
            name="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            placeholder="Please enter password"
            id="password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? "Logging in..." : " Login"}
        </button>
      </form>
      <button
      //onClick={() => navigate("/register")}
      >
        Don't have an account? Register here.
      </button>
    </>
  );
};

export default LoginForm;
