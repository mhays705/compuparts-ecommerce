import { Row, Col, Container, Button, Card } from "react-bootstrap";
import DashboardCard from "../dashboardCard/DashboardCard";

const customerDashboard = () => {
  return (
    <Container
      fluid
      className="d-flex bg-img vh-100 justify-content-center align-items-center"
    >
      <Container
        fluid
        className="d-flex bg-secondary w-50 border rounded p-4 justify-content-center align-items-center"
      >
        <Row className="g-5 d-flex align-items-center justify-content-center">
          <Col
            className="d-flex align-items-center justify-content-center mx-3"
            aria-label="check order status"
          >
            <DashboardCard title="Check Order Status" />
          </Col>
          <Col
            className="d-flex align-items-center justify-content-center mx-5"
            ario-label="view products"
          >
            <DashboardCard title="View Products" />
          </Col>
          <Col
            className="d-flex align-items-center justify-content-center mx-3"
            aria-label="manage account"
          >
            <DashboardCard title="Manage Account" />
          </Col>
        </Row>
      </Container>
    </Container>
  );
};

export default customerDashboard;
