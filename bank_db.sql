/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.6.21 : Database - bank_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`bank_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `bank_db`;

/*Table structure for table `tabungan_tbl` */

DROP TABLE IF EXISTS `tabungan_tbl`;

CREATE TABLE `tabungan_tbl` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `nik` varchar(30) DEFAULT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `kredit` int(11) DEFAULT NULL,
  `debet` int(11) DEFAULT NULL,
  `saldo` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `craete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `tabungan_tbl` */

insert  into `tabungan_tbl`(`id`,`nik`,`nama`,`kredit`,`debet`,`saldo`,`createTime`,`craete_time`) values (19,'12345','putri',1000,0,1000,NULL,'2019-09-29 11:02:06'),(20,'54321','helma',2500,0,2500,NULL,'2019-09-29 11:04:50');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
