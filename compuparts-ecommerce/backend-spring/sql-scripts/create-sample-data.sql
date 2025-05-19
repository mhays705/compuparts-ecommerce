
USE `inventory_manager`;


SET FOREIGN_KEY_CHECKS=0;

-- User Table
INSERT INTO `users` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `company_name`, `enabled`)
VALUES (1, 'admin', '$2a$12$NhctKYQBYSBioQBxIICWfe/EOgFHDtVmFA26LrsCs1uwpUY.d0JMO', 'user@example.com', 'John', 'Doe', 'Doe Inc.', 1);

-- Category Table
INSERT INTO `categories` (`id`, `name`)
VALUES (1, 'CPU');

-- Manufacturer Table
INSERT INTO `manufacturers` (`id`, `name`)
VALUES (1, 'AMD');

-- Part Table

INSERT INTO `products` (`id`, `name`, `sku`, `category_id`, `manufacturer_id`, `price`, `description`, `image_url`, `stock`)
VALUES (1, 'Ryzen 9 5900x', 'MCX1-001', 1, 1, 199.99, '12 core, 24 thread, 4.5ghz', 'http://example.com/images/mcx1.jpg', 150);

-- Order Table

INSERT INTO `orders` (`id`, `order_number`, `customer_id`, `order_date`, `status`, `total`, `created_at`, `updated_at`)
VALUES (1, 'ORD20250514-001', 1, CURDATE(), 'Pending', 199.99, NOW(), NOW());

-- Order Items Table

INSERT INTO `order_items` (`id`, `order_id`, `product_id`, `quantity`, `price_at_order`)
VALUES (1, 1, 1, 1, 199.99);

-- Role Table
INSERT INTO `roles` (`id`, `name`)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO `roles` (`id`, `name`)
VALUES (2, 'ROLE_STAFF');
INSERT INTO `roles`(`id`, `name`)
VALUES (3, 'ROLE_CUSTOMER');



INSERT INTO `user_roles` (`user_id`, `role_id`)
VALUES (1, 1); 


SET FOREIGN_KEY_CHECKS=1;

