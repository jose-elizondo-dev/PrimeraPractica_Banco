/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 8.0.44 : Database - banco
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`banco` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `banco`;

/*Table structure for table `administrador` */

DROP TABLE IF EXISTS `administrador`;

CREATE TABLE `administrador` (
  `cedula` varchar(20) NOT NULL,
  `nombreCompleto` varchar(100) NOT NULL,
  `correoElectronico` varchar(100) NOT NULL,
  `contrasenia` varchar(255) NOT NULL,
  PRIMARY KEY (`cedula`),
  UNIQUE KEY `correoElectronico` (`correoElectronico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `administrador` */

insert  into `administrador`(`cedula`,`nombreCompleto`,`correoElectronico`,`contrasenia`) values 
('123456789','jose','wasd','1234'),
('qq','qq','qq','qq');

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `cedula` varchar(20) NOT NULL,
  `nombreCompleto` varchar(100) NOT NULL,
  `correoElectronico` varchar(100) NOT NULL,
  `contrasenia` varchar(255) NOT NULL,
  `sexo` char(1) DEFAULT NULL,
  `profesion` varchar(100) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cedula`),
  UNIQUE KEY `correoElectronico` (`correoElectronico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cliente` */

insert  into `cliente`(`cedula`,`nombreCompleto`,`correoElectronico`,`contrasenia`,`sexo`,`profesion`,`direccion`) values 
('12345','juanito','correo','1234','M','Cajero','Casa azul'),
('1234567890','pedrito','correo1','1234','M','Secretario','Calle 1');

/*Table structure for table `cuenta` */

DROP TABLE IF EXISTS `cuenta`;

CREATE TABLE `cuenta` (
  `numeroCuenta` varchar(20) NOT NULL,
  `tipo` enum('AHORRO','DEBITO','CREDITO') NOT NULL,
  `saldo` decimal(15,2) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `activa` tinyint(1) NOT NULL DEFAULT '1',
  `cedulaCliente` varchar(20) NOT NULL,
  PRIMARY KEY (`numeroCuenta`),
  KEY `cedulaCliente` (`cedulaCliente`),
  CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`cedulaCliente`) REFERENCES `cliente` (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cuenta` */

insert  into `cuenta`(`numeroCuenta`,`tipo`,`saldo`,`fechaCreacion`,`activa`,`cedulaCliente`) values 
('1234','AHORRO',101.50,'2025-11-29',1,'12345'),
('12345','DEBITO',30.30,'2025-11-29',1,'12345'),
('123456','CREDITO',0.00,'2025-11-29',1,'12345'),
('AH202511285263','AHORRO',205.00,'2025-11-29',1,'12345');

/*Table structure for table `cuentaahorro` */

DROP TABLE IF EXISTS `cuentaahorro`;

CREATE TABLE `cuentaahorro` (
  `numeroCuenta` varchar(20) NOT NULL,
  `porcentajeInteres` decimal(5,2) NOT NULL,
  PRIMARY KEY (`numeroCuenta`),
  CONSTRAINT `cuentaahorro_ibfk_1` FOREIGN KEY (`numeroCuenta`) REFERENCES `cuenta` (`numeroCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cuentaahorro` */

insert  into `cuentaahorro`(`numeroCuenta`,`porcentajeInteres`) values 
('1234',1.50),
('AH202511285263',2.50);

/*Table structure for table `cuentacredito` */

DROP TABLE IF EXISTS `cuentacredito`;

CREATE TABLE `cuentacredito` (
  `numeroCuenta` varchar(20) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `limiteCredito` decimal(15,2) NOT NULL,
  PRIMARY KEY (`numeroCuenta`),
  CONSTRAINT `cuentacredito_ibfk_1` FOREIGN KEY (`numeroCuenta`) REFERENCES `cuenta` (`numeroCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cuentacredito` */

insert  into `cuentacredito`(`numeroCuenta`,`tipo`,`limiteCredito`) values 
('123456','Cashback',500.00);

/*Table structure for table `cuentadebito` */

DROP TABLE IF EXISTS `cuentadebito`;

CREATE TABLE `cuentadebito` (
  `numeroCuenta` varchar(20) NOT NULL,
  `porcentajeInteres` decimal(5,2) NOT NULL,
  PRIMARY KEY (`numeroCuenta`),
  CONSTRAINT `cuentadebito_ibfk_1` FOREIGN KEY (`numeroCuenta`) REFERENCES `cuenta` (`numeroCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cuentadebito` */

insert  into `cuentadebito`(`numeroCuenta`,`porcentajeInteres`) values 
('12345',1.00);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
