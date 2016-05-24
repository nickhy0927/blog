/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.5.28 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog`;

/*Table structure for table `t_p_attach` */

DROP TABLE IF EXISTS `t_p_attach`;

CREATE TABLE `t_p_attach` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_status` int(11) DEFAULT NULL,
  `display_status` int(11) DEFAULT NULL,
  `file_size` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_p_attach` */

/*Table structure for table `t_p_category` */

DROP TABLE IF EXISTS `t_p_category`;

CREATE TABLE `t_p_category` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `valid_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_p_category` */

/*Table structure for table `t_p_menu` */

DROP TABLE IF EXISTS `t_p_menu`;

CREATE TABLE `t_p_menu` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_status` int(11) DEFAULT NULL,
  `display_status` int(11) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `p_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o02lae57wqyhe25k87vlno7f0` (`p_id`),
  CONSTRAINT `FK_o02lae57wqyhe25k87vlno7f0` FOREIGN KEY (`p_id`) REFERENCES `t_p_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_p_menu` */

insert  into `t_p_menu`(`id`,`create_time`,`update_time`,`delete_status`,`display_status`,`authority`,`href`,`name`,`p_id`) values ('1094327a-8589-4a00-aef4-0f9329152aa0','2016-05-24 16:21:51','2016-05-24 16:21:51',0,0,'userList','/admin/platform/user/list.html','用户列表','74294613-3602-4e6a-bd5f-c53c931070e4'),('46d5a2e8-6e50-41cf-b2f0-9e5fa353832f','2016-05-24 16:19:04','2016-05-24 16:19:04',0,0,'platform','#','模块',NULL),('74294613-3602-4e6a-bd5f-c53c931070e4','2016-05-24 16:21:20','2016-05-24 16:21:21',0,0,'userManage','#','用户管理','46d5a2e8-6e50-41cf-b2f0-9e5fa353832f'),('8221f877-5342-4784-b04e-77d35fd2788b','2016-05-24 16:22:39','2016-05-24 16:22:39',0,0,'userCreate','#','用户新增','74294613-3602-4e6a-bd5f-c53c931070e4');

/*Table structure for table `t_p_role` */

DROP TABLE IF EXISTS `t_p_role`;

CREATE TABLE `t_p_role` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_status` int(11) DEFAULT NULL,
  `display_status` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_p_role` */

insert  into `t_p_role`(`id`,`create_time`,`update_time`,`delete_status`,`display_status`,`code`,`name`) values ('16d1f346-70af-4cc1-b7ec-a03bb849a6a1','2016-05-24 16:18:03','2016-05-24 16:18:03',0,0,'20160524161752440','管理员'),('d1149716-5d32-47ec-a113-3f825ed47efe','2016-05-24 16:18:19','2016-05-24 16:18:19',0,0,'20160524161807650','普通用户');

/*Table structure for table `t_p_role_menu` */

DROP TABLE IF EXISTS `t_p_role_menu`;

CREATE TABLE `t_p_role_menu` (
  `role_id` varchar(255) NOT NULL,
  `menu_id` varchar(255) NOT NULL,
  KEY `FK_9obcilnf5wgey7gxmw4w3u9pp` (`menu_id`),
  KEY `FK_ji8pq8phg730anygflj1rvd5p` (`role_id`),
  CONSTRAINT `FK_ji8pq8phg730anygflj1rvd5p` FOREIGN KEY (`role_id`) REFERENCES `t_p_role` (`id`),
  CONSTRAINT `FK_9obcilnf5wgey7gxmw4w3u9pp` FOREIGN KEY (`menu_id`) REFERENCES `t_p_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_p_role_menu` */

/*Table structure for table `t_p_user_fri` */

DROP TABLE IF EXISTS `t_p_user_fri`;

CREATE TABLE `t_p_user_fri` (
  `user_id` varchar(255) NOT NULL,
  `fr_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`fr_id`),
  KEY `FK_r2o5vfj17t5wpfeamtrvwk99w` (`fr_id`),
  CONSTRAINT `FK_rod4bw2f60uebnl1836xi29e8` FOREIGN KEY (`user_id`) REFERENCES `t_p_users` (`id`),
  CONSTRAINT `FK_r2o5vfj17t5wpfeamtrvwk99w` FOREIGN KEY (`fr_id`) REFERENCES `t_p_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_p_user_fri` */

/*Table structure for table `t_p_user_role` */

DROP TABLE IF EXISTS `t_p_user_role`;

CREATE TABLE `t_p_user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_mk0l42w3v5e0xkrfxpa97w3bv` (`role_id`),
  CONSTRAINT `FK_8np60bshs5317b62gbrbqsyt4` FOREIGN KEY (`user_id`) REFERENCES `t_p_users` (`id`),
  CONSTRAINT `FK_mk0l42w3v5e0xkrfxpa97w3bv` FOREIGN KEY (`role_id`) REFERENCES `t_p_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_p_user_role` */

/*Table structure for table `t_p_users` */

DROP TABLE IF EXISTS `t_p_users`;

CREATE TABLE `t_p_users` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_status` int(11) DEFAULT NULL,
  `display_status` int(11) DEFAULT NULL,
  `brithday` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `resources` varchar(255) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL,
  `user_tag` varchar(255) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_p_users` */

insert  into `t_p_users`(`id`,`create_time`,`update_time`,`delete_status`,`display_status`,`brithday`,`email`,`login_name`,`nick_name`,`password`,`resources`,`user_status`,`user_tag`,`user_type`) values ('92295687-0ba7-4773-ab60-2f39c066d22b','2016-05-24 16:16:53','2016-05-24 16:16:53',0,0,'2016-06-07','h_y_12@163.com','huangyuan','黄园','a4b5de5d84abd1f3b7030c22d70de1c4','',0,'',1);

/*Table structure for table `t_topic_column` */

DROP TABLE IF EXISTS `t_topic_column`;

CREATE TABLE `t_topic_column` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_status` int(11) DEFAULT NULL,
  `display_status` int(11) DEFAULT NULL,
  `column_type` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  `column_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l1sfu2dj7q6v8dnrsp6gt3myp` (`column_id`),
  CONSTRAINT `FK_l1sfu2dj7q6v8dnrsp6gt3myp` FOREIGN KEY (`column_id`) REFERENCES `t_topic_column` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_topic_column` */

/*Table structure for table `t_topic_info` */

DROP TABLE IF EXISTS `t_topic_info`;

CREATE TABLE `t_topic_info` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_status` int(11) DEFAULT NULL,
  `display_status` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `column_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7xqixo5sov1o5em0v5at4ypbq` (`column_id`),
  KEY `FK_fwgc4hlgjthrdt0nenqs1v12e` (`user_id`),
  CONSTRAINT `FK_fwgc4hlgjthrdt0nenqs1v12e` FOREIGN KEY (`user_id`) REFERENCES `t_p_users` (`id`),
  CONSTRAINT `FK_7xqixo5sov1o5em0v5at4ypbq` FOREIGN KEY (`column_id`) REFERENCES `t_topic_column` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_topic_info` */

/*Table structure for table `t_user_note` */

DROP TABLE IF EXISTS `t_user_note`;

CREATE TABLE `t_user_note` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_status` int(11) DEFAULT NULL,
  `display_status` int(11) DEFAULT NULL,
  `note_content` longtext,
  `note_number` varchar(255) DEFAULT NULL,
  `note_title` varchar(255) DEFAULT NULL,
  `column_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jg81v4k7inrihwejq4a3oa2du` (`column_id`),
  KEY `FK_sn1g81eo2sxl66aw9eir53qp1` (`user_id`),
  CONSTRAINT `FK_sn1g81eo2sxl66aw9eir53qp1` FOREIGN KEY (`user_id`) REFERENCES `t_p_users` (`id`),
  CONSTRAINT `FK_jg81v4k7inrihwejq4a3oa2du` FOREIGN KEY (`column_id`) REFERENCES `t_topic_column` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_note` */

/*Table structure for table `t_user_reply` */

DROP TABLE IF EXISTS `t_user_reply`;

CREATE TABLE `t_user_reply` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_status` int(11) DEFAULT NULL,
  `display_status` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `user_note_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nfo0if44yke29vue1g5jk1kyg` (`user_id`),
  KEY `FK_jbbdphp5bduof3kvgcjsxiguq` (`user_note_id`),
  CONSTRAINT `FK_jbbdphp5bduof3kvgcjsxiguq` FOREIGN KEY (`user_note_id`) REFERENCES `t_user_note` (`id`),
  CONSTRAINT `FK_nfo0if44yke29vue1g5jk1kyg` FOREIGN KEY (`user_id`) REFERENCES `t_p_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_reply` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
