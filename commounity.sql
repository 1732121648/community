/*
SQLyog Community v12.3.3 (64 bit)
MySQL - 5.5.54 : Database - community
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`community` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `community`;

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` text,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `comment_count` int(11) DEFAULT '0',
  `view_count` int(11) DEFAULT '0',
  `like_count` int(11) DEFAULT '0',
  `tag` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `question` */

insert  into `question`(`id`,`title`,`description`,`gmt_create`,`gmt_modified`,`creator`,`comment_count`,`view_count`,`like_count`,`tag`) values 
(1,'明早有一个会议','内容',NULL,NULL,NULL,0,0,0,'标签'),
(2,'标题2','内容2',NULL,NULL,NULL,0,0,0,'标签2'),
(3,'明早有一个会议','内容3',1571245446920,1571245446920,5,0,0,0,'标签23');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `token` char(36) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `avatar_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`account_id`,`name`,`token`,`gmt_create`,`gmt_modified`,`avatar_url`) values 
(1,'1','张三','1',1,1,NULL),
(2,'50724168','LinCy','4d984381-61e9-4daa-b6f9-e8b0904bd757',1571150089229,1571150089229,NULL),
(3,'50724168','LinCy','6e047559-76df-4017-8759-8e35b00fff4b',1571150163222,1571150163222,NULL),
(4,'50724168','LinCy','8a7c95ff-0d23-446e-93b5-bfc4408ce7e8',1571152093296,1571152093296,NULL),
(5,'50724168','LinCy','1900a888-2f4c-4170-9659-e2dbbd80390d',1571244266382,1571244266382,NULL),
(6,'50724168','LinCy','7601ab72-2074-4546-84db-c45eb2722c5f',1571498373625,1571498373625,'https://avatars1.githubusercontent.com/u/50724168?v=4');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
