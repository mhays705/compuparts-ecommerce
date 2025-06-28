import { Container, Col, Row, Button, Image } from "react-bootstrap";
import heroImage from "../../assets/images/heroImg.jpg";

const Hero = ({ setShowRegister }) => {
  const handleClick = () => {
    setShowRegister(true);
  };

  return (
    <>
      <Container fluid className="bg-img text-light vh-100">
        <Container fluid className="h-100">
          <Row className="h-100 d-flex align-items-center justify-content-center ">
            <Col md={6} className="text-center">
              <h1 className="display-4">Welcome to CompuParts!</h1>
              <p className="lead">
                Come shop with us for parts at all time low prices!
              </p>
              <Button onClick={handleClick} variant="primary" size="lg">
                Start Shopping! Register Now!
              </Button>
            </Col>
            <Col md={6}>
              <Image
                src={heroImage}
                alt="hero page image"
                rounded
                fluid
                style={{ maxWidth: "600px", height: "auto", width: "100%" }}
              ></Image>
            </Col>
          </Row>
        </Container>
      </Container>
    </>
  );
};

export default Hero;
