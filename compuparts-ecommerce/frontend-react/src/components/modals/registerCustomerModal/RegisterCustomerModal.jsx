import { useState, useEffect } from "react";
import axios from "axios";
import { Alert, Form, Row, Col, Modal, Button, Spinner } from "react-bootstrap";

const RegisterUserModal = ({ showRegister, onHide, setShowLogin }) => {
  const [newUser, setNewUser] = useState({
    username: "",
    password: "",
    passwordConfirm: "",
    email: "",
    firstName: "",
    lastName: "",
    company: "",
  });
  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const [showSuccess, setShowSuccess] = useState(false);
  const [validated, setValidated] = useState(false);

  useEffect(() => {
    setNewUser({
      username: "",
      password: "",
      passwordConfirm: "",
      email: "",
      firstName: "",
      lastName: "",
      company: "",
    });
    setErrors({});
    setLoading(false);
    setShowSuccess(false);
    setValidated(false);
  }, [showRegister]);

  const url = "http://localhost:8080/api/users/customer";

  const handleSumbit = async (e) => {
    e.preventDefault();
    setErrors({});

    const form = e.currentTarget;

    if (form.checkValidity() === false) {
      e.stopPropagation();
      setValidated(true); // triggers Bootstrap feedback
      return;
    }

    setLoading(true);

    try {
      await axios.post(url, newUser);
      setShowSuccess(true);
      onHide();
      setTimeout(() => setShowSuccess(false), 3000);
    } catch (err) {
      const backendErrors = err.response?.data?.errors;
      if (backendErrors) {
        setErrors(backendErrors);
      } else {
        const msg =
          err.response?.data?.message ||
          err.message ||
          "Something went wrong. Please try again.";
        setErrors({ general: msg });
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
          User sucessfully registered!
        </Alert>
      )}

      <Modal show={showRegister} onHide={onHide} centered>
        <Modal.Header closeButton>
          <Modal.Title>New User Registration</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          {errors.general && (
            <Alert variant="danger" className="mt-2">
              {errors.general}
            </Alert>
          )}
          <Form noValidate validated={validated} onSubmit={handleSumbit}>
            <Form.Group as={Row} className="mb-3" controlId="username">
              <Form.Label column sm={3}>
                Username
              </Form.Label>
              <Col sm={9}>
                <Form.Control
                  type="text"
                  placeholder="Enter username"
                  value={newUser.username}
                  onChange={(e) =>
                    setNewUser({ ...newUser, username: e.target.value })
                  }
                  required
                  isInvalid={!!errors.username}
                  disabled={loading}
                ></Form.Control>
                <Form.Control.Feedback type="invalid">
                  {errors.username || "Please enter a username."}
                </Form.Control.Feedback>
              </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3" controlId="password">
              <Form.Label column sm={3}>
                Password
              </Form.Label>
              <Col sm={9}>
                <Form.Control
                  type="password"
                  placeholder="Enter password"
                  value={newUser.password}
                  onChange={(e) =>
                    setNewUser({ ...newUser, password: e.target.value })
                  }
                  isInvalid={!!errors.password}
                  required
                  disabled={loading}
                ></Form.Control>
                <Form.Control.Feedback type="invalid">
                  {errors.password || "Please enter a password."}
                </Form.Control.Feedback>
              </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3" controlId="passwordConfirm">
              <Form.Label column sm={3}>
                Password Confirmation
              </Form.Label>
              <Col sm={9}>
                <Form.Control
                  type="password"
                  placeholder="Confirm password"
                  value={newUser.passwordConfirm}
                  onChange={(e) =>
                    setNewUser({ ...newUser, passwordConfirm: e.target.value })
                  }
                  isInvalid={!!errors.passwordConfirm}
                  required
                  disabled={loading}
                ></Form.Control>
                <Form.Control.Feedback type="invalid">
                  {errors.passwordConfirm || "Please confirm password."}
                </Form.Control.Feedback>
              </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3" controlId="email">
              <Form.Label column sm={3}>
                Email
              </Form.Label>
              <Col sm={9}>
                <Form.Control
                  type="email"
                  placeholder="Enter email"
                  value={newUser.email}
                  onChange={(e) =>
                    setNewUser({ ...newUser, email: e.target.value })
                  }
                  isInvalid={!!errors.email}
                  required
                  disabled={loading}
                ></Form.Control>
                <Form.Control.Feedback type="invalid">
                  {errors.email || "Please enter email."}
                </Form.Control.Feedback>
              </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3" controlId="firstName">
              <Form.Label column sm={3}>
                First name
              </Form.Label>
              <Col sm={9}>
                <Form.Control
                  type="text"
                  placeholder="Enter first name"
                  value={newUser.firstName}
                  onChange={(e) =>
                    setNewUser({ ...newUser, firstName: e.target.value })
                  }
                  isInvalid={!!errors.firstName}
                  required
                  disabled={loading}
                ></Form.Control>
                <Form.Control.Feedback type="invalid">
                  {errors.firstName || "Please enter a first name."}
                </Form.Control.Feedback>
              </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3" controlId="lastName">
              <Form.Label column sm={3}>
                Last name
              </Form.Label>
              <Col sm={9}>
                <Form.Control
                  type="text"
                  placeholder="Enter last name"
                  value={newUser.lastName}
                  onChange={(e) =>
                    setNewUser({ ...newUser, lastName: e.target.value })
                  }
                  isInvalid={!!errors.lastName}
                  required
                  disabled={loading}
                ></Form.Control>
                <Form.Control.Feedback type="invalid">
                  {errors.lastName || "Please enter a last name"}
                </Form.Control.Feedback>
              </Col>
            </Form.Group>
            <Form.Group as={Row} className="mb-3" controlId="company">
              <Form.Label column sm={3}>
                Company
              </Form.Label>
              <Col sm={9}>
                <Form.Control
                  type="text"
                  placeholder="Enter company name (Optional)"
                  value={newUser.company}
                  onChange={(e) =>
                    setNewUser({ ...newUser, company: e.target.value })
                  }
                  isInvalid={!!errors.company}
                  disabled={loading}
                ></Form.Control>
                <Form.Control.Feedback type="invalid">
                  {errors.company}
                </Form.Control.Feedback>
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
                    Registering...
                  </>
                ) : (
                  "Register"
                )}
              </Button>
            </div>
          </Form>
        </Modal.Body>

        <Modal.Footer>
          <small className="text-muted">
            Already have an account?{" "}
            <a
              href="#"
              className="text-primary"
              onClick={(e) => {
                e.preventDefault();
                onHide();
                setShowLogin(true);
              }}
            >
              Sign In Here!
            </a>
          </small>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default RegisterUserModal;
