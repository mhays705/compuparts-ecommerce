import { Card } from "react-bootstrap";

import "./dashboardCard.css";

const DashboardCard = ({ title }) => {
  return (
    <Card className="clickable-card dashboard-card bg-dark  text-light">
      <Card.Body className="d-flex justify-content-center align-items-center">
        {title}
      </Card.Body>
    </Card>
  );
};

export default DashboardCard;
