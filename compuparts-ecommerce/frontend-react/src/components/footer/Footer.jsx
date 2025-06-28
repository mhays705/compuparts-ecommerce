import { Container, Row, Col } from "react-bootstrap";

const Footer = () => {
  return (
    <footer className="bg-dark text-light py-4 mt-auto">
      <Container>
        <Row className="justify-content-center align-items-center">
          <Col md={6} className="text-md-start text-center">
            <p className="mb-0">&copy; {new Date().getFullYear()} CompuParts</p>
          </Col>
          <Col md={6} className="text-md-end">
            <a href="/privacy" className="text-light me-3">
              About Us
            </a>
            <a href="/privacy" className="text-light me-3">
              Privacy
            </a>
            <a href="/terms" className="text-light">
              Terms
            </a>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
