
CREATE TABLE `product` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
`name` varchar(100) NOT NULL,
`price` double NOT NULL,
`unit` varchar(20) NOT NULL,
`image` varchar(255) NOT NULL,
`description` varchar(255) DEFAULT NULL,
`production_date` datetime NOT NULL,
`production_place` varchar(20) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
`create_date` datetime NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_item` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
`product_id` BIGINT(20) NOT NULL,
`order_id` BIGINT(20)NOT NULL,
`count` INT(20)NOT NULL,
PRIMARY KEY(`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;
