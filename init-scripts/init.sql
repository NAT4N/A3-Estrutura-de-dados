CREATE DATABASE IF NOT EXISTS a3;

use a3;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` binary(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
);

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_transacao` varchar(255) DEFAULT NULL,
  `id_cliente` varchar(255) DEFAULT NULL,
  `id_transacao` varchar(255) DEFAULT NULL,
  `produtos` json DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);