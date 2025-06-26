import React, { useEffect, useState } from "react";
import { Modal, Button, Form, Alert, Spinner, Row, Col } from "react-bootstrap";
import axios from "axios";

const LoginModal = ({ show, onHide, setLoggedIn }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [showSuccess, setShowSuccess] = useState(false);

  useEffect(() => {
    setUsername("");
    setPassword("");
    setError("");
    setLoading(false);
  }, [show]);

  const url = "http://localhost:8080/api/users/login";

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const res = await axios.post(url, { username, password });
      localStorage.setItem("token", res.data.token);
      setLoggedIn(true);
      setShowSuccess(true);
      onHide(); // Close the modal
      setTimeout(() => setShowSuccess(false), 3000);
    } catch (err) {
      if (err.response && err.response.status === 401) {
        setError("Invalid username or password");
      } else {
        setError("An unexpected error occurred");
        console.error(err);
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      {showSuccess && (
        <Alert
          variant="success"
          onClose={() => setShowSuccess(false)}
          dismissible
        >
          Successfully Logged In!
        </Alert>
      )}
      <Modal show={show} onHide={onHide} centered>
        <Modal.Header closeButton>
          <Modal.Title>Login</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          {error && <Alert variant="danger">{error}</Alert>}

          <Form onSubmit={handleSubmit}>
            <Form.Group as={Row} className="mb-3" controlId="username">
              <Form.Label column sm={3}>
                Username:
              </Form.Label>
              <Col sm={9}>
                <Form.Control
                  type="text"
                  placeholder="Enter username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                  disabled={loading}
                />
              </Col>
            </Form.Group>

            <Form.Group as={Row} className="mb-3" controlId="password">
              <Form.Label column sm={3}>
                Password:
              </Form.Label>
              <Col sm={9}>
                <Form.Control
                  type="password"
                  placeholder="Enter password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                  disabled={loading}
                />
              </Col>
            </Form.Group>

            <div className="d-grid">
              <Button variant="primary" type="submit" disabled={loading}>
                {loading ? (
                  <>
                    <Spinner
                      as="span"
                      animation="border"
                      size="sm"
                      role="status"
                      aria-hidden="true"
                    />{" "}
                    Logging in...
                  </>
                ) : (
                  "Login"
                )}
              </Button>
            </div>
          </Form>
        </Modal.Body>

        <Modal.Footer>
          <small className="text-muted">
            Don't have an account?{" "}
            <a href="/register" className="text-primary">
              Register Here!
            </a>
          </small>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default LoginModal;
