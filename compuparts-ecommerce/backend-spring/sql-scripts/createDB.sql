drop schema if exists `inventory_manager`;
create schema `inventory_manager`;

use `inventory_manager`;

drop table if exists `users`;
create table `users` (
    `id` bigint not null auto_increment,
    `username` varchar(45) default null,
    `password` varchar(255) default null,
    `email` varchar(255) default null,
    `first_name` varchar(45) default null,
    `last_name` varchar(45) default null,
    `company_name` varchar(45) default null,
    `enabled` TINYINT(1) default 1,
    unique key `USERNAME_UNIQUE`(`username`),
    primary key (`id`)
) engine=INNODB auto_increment=1 default charset=latin1;

drop table if exists `categories`;
create table `categories` (
    `id` bigint not null auto_increment,
    `name` varchar(255) not null,
    primary key (`id`)
) engine=INNODB auto_increment=1 default charset=latin1;

drop table if exists `manufacturers`;
create table `manufacturers` (
    `id` bigint not null auto_increment,
    `name` varchar(255) not null,
    primary key (`id`)
) engine=INNODB auto_increment=1 default charset=latin1;


drop table if exists `products`;
create table `products` (
    `id` bigint not null auto_increment,
    `name` varchar(255) default null,
    `sku` varchar(255) default null,
    `category_id` bigint not null,
    `manufacturer_id` bigint,
    `price` decimal(10,2) default null,
    `description` text default null,
    `image_url` varchar(500),
    `stock` int default null,

    primary key (`id`),

    key FK_CATEGORY_idx (`category_id`),
    key FK_MANUFACTURER_idx (`manufacturer_id`),

    constraint `FK_PART_CATEGORY`
    foreign key (`category_id`) references `categories`(`id`)
    on delete no action on update no action,

    constraint `FK_PART_MANUFACTURER`
    foreign key (`manufacturer_id`) references `manufacturers`(`id`)
    on delete no action on update no action

) engine=INNODB auto_increment=1 default charset=latin1;

set foreign_key_checks=1;

drop table if exists `orders`;
create table `orders` (
    `id` bigint not null auto_increment,
    `order_number` varchar(255) default null,
    `customer_id` bigint not null,
    `order_date` date,
    `status` varchar(45) default null,
    `total` decimal(10,2) default null,
    `created_at` datetime default current_timestamp,
    `updated_at` datetime default current_timestamp on update current_timestamp,
    primary key (`id`),
	key FK_ORDER_CUSTOMER_idx (`customer_id`),
    
    constraint `FK_ORDER_CUSTOMER`
    foreign key (`customer_id`) references `users`(`id`)
    on delete no action on update no action
) engine=INNODB auto_increment=1 charset=latin1;

drop table if exists `order_items`;
create table `order_items` (
    `id` int not null auto_increment,
    `order_id` bigint not null,
    `product_id` bigint not null,
    `quantity` int default null,
    `price_at_order` decimal(10,2),

    primary key (`id`),
    key FK_ORDER_idx (`order_id`),
    key FK_PART_idx (`product_id`),

    constraint `FK_ORDER`
    foreign key (`order_id`) references `orders`(`id`)
    on update no action on delete no action,

    constraint `FK_PART`
    foreign key (`product_id`) references `products`(`id`)
) engine=INNODB auto_increment=1 charset=latin1;

drop table if exists `roles`;
create table `roles` (
    `id` bigint not null auto_increment,
    `name` varchar(45) default null,
    primary key(`id`)
) engine=INNODB auto_increment=1 charset=latin1;

drop table if exists `user_roles`;
create table `user_roles` (
    `user_id` bigint not null,
    `role_id` bigint not null,
    primary key (`user_id`, `role_id`),

    key FK_USER_idx(`user_id`),
    key FK_ROLE_idx(`role_id`),

    constraint FK_USER
    foreign key (`user_id`) references `users`(`id`)
    on update no action on delete no action,

    constraint FK_ROLE
    foreign key (`role_id`) references `roles`(`id`)
    on update no action on delete no action
) engine=INNODB charset=latin1;