-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: mallUser
-- ------------------------------------------------------
-- Server version       8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `mall_interface`
--

DROP TABLE IF EXISTS `mall_interface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_interface` (
  `inter_id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限接口id',
  `inter_name` varchar(50) NOT NULL DEFAULT '' COMMENT '权限地址',
  `inter_info` varchar(50) NOT NULL DEFAULT '' COMMENT '权限简介',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int NOT NULL DEFAULT '0' COMMENT '创建者id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最新修改时间',
  `update_user` int DEFAULT '0' COMMENT '修改者id',
  PRIMARY KEY (`inter_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_interface`
--

LOCK TABLES `mall_interface` WRITE;
/*!40000 ALTER TABLE `mall_interface` DISABLE KEYS */;
INSERT INTO `mall_interface` VALUES (1,'/admin/**','管理员权限','2021-03-13 16:40:16',0,'2021-03-16 21:43:18',0),(2,'/businessAdmin/**','业务管理员权限','2021-03-13 16:41:21',0,'2021-04-18 12:16:27',0),(3,'/marketAdmin/**','营销管理员权限','2021-03-13 16:41:39',0,'2021-04-18 12:16:27',0),(4,'/user/**','用户访问权限','2021-03-13 16:43:30',0,'2021-03-16 21:43:18',0),(5,'/tourist/**','游客访问权限','2021-03-13 16:43:46',0,'2021-03-16 21:43:18',0);
/*!40000 ALTER TABLE `mall_interface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_role`
--

DROP TABLE IF EXISTS `mall_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `role_info` varchar(50) NOT NULL DEFAULT '' COMMENT '角色简介',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int NOT NULL DEFAULT '0' COMMENT '创建者id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最新修改时间',
  `update_user` int DEFAULT '0' COMMENT '修改者id',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_role`
--

LOCK TABLES `mall_role` WRITE;
/*!40000 ALTER TABLE `mall_role` DISABLE KEYS */;
INSERT INTO `mall_role` VALUES (1,'ADMIN','管理员系统','2021-03-13 16:46:28',0,'2021-03-13 16:46:28',0),(2,'BusinessAdmin','业务管理员系统','2021-03-13 16:46:52',0,'2021-04-18 12:14:39',0),(3,'MarketAdmin','营销管理员系统','2021-03-13 16:47:13',0,'2021-04-18 12:14:39',0),(4,'USER','用户角色','2021-03-13 16:47:38',0,'2021-03-13 16:47:38',0),(5,'TOURIST','游客角色','2021-03-13 16:47:54',0,'2021-03-13 16:47:54',0);
/*!40000 ALTER TABLE `mall_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_role_interface`
--

DROP TABLE IF EXISTS `mall_role_interface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_role_interface` (
  `ui_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `inter_id` bigint NOT NULL COMMENT '权限id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int NOT NULL DEFAULT '0' COMMENT '创建者id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最新修改时间',
  `update_user` int DEFAULT '0' COMMENT '修改者id',
  PRIMARY KEY (`ui_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_role_interface`
--

LOCK TABLES `mall_role_interface` WRITE;
/*!40000 ALTER TABLE `mall_role_interface` DISABLE KEYS */;
INSERT INTO `mall_role_interface` VALUES (1,1,1,'2021-03-13 16:46:28',0,'2021-03-13 16:46:28',0),(2,2,2,'2021-03-13 16:46:52',0,'2021-03-13 16:46:52',0),(3,3,3,'2021-03-13 16:47:13',0,'2021-03-13 16:47:13',0),(4,4,4,'2021-03-13 16:47:38',0,'2021-03-13 16:47:38',0),(5,5,5,'2021-03-13 16:47:54',0,'2021-03-13 16:47:54',0);
/*!40000 ALTER TABLE `mall_role_interface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_user`
--

DROP TABLE IF EXISTS `mall_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `login_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户登录名',
  `password_md5` varchar(100) NOT NULL DEFAULT '' COMMENT 'MD5加密后的密码',
  `phone_number` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '注销标识字段(0-正常 1-已注销)',
  `locked_flag` tinyint NOT NULL DEFAULT '0' COMMENT '锁定标识字段(0-未锁定 1-已锁定)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_user`
--

LOCK TABLES `mall_user` WRITE;
/*!40000 ALTER TABLE `mall_user` DISABLE KEYS */;
INSERT INTO `mall_user` VALUES (1,'admin','$2a$10$8hBFFZoJaKjEfS0JIvdRh.KFaWdhIort5cb3JF5md1ltbSaK4vUDe','1359003547',0,0,'2021-03-13 16:48:51'),(2,'BusinessAdmin','$2a$10$8hBFFZoJaKjEfS0JIvdRh.KFaWdhIort5cb3JF5md1ltbSaK4vUDe','1359003546',0,1,'2021-03-13 16:50:05'),(3,'MarketAdmin','$2a$10$8hBFFZoJaKjEfS0JIvdRh.KFaWdhIort5cb3JF5md1ltbSaK4vUDe','1359003546',0,0,'2021-03-13 16:50:15'),(4,'user','$2a$10$8hBFFZoJaKjEfS0JIvdRh.KFaWdhIort5cb3JF5md1ltbSaK4vUDe','1359003546',0,0,'2021-03-13 16:50:26'),(5,'test','$2a$10$8hBFFZoJaKjEfS0JIvdRh.KFaWdhIort5cb3JF5md1ltbSaK4vUDe','1236599494',1,0,'2021-03-24 10:43:11'),(8,'lirisheng','$2a$10$Dh6ZHSeaQbUxqaAP5dJ5oegOfG43C9TBPS1zQZQg3lb3ILjjjuTNC','13590035440',0,1,'2021-05-09 16:40:53');
/*!40000 ALTER TABLE `mall_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_user_address`
--

DROP TABLE IF EXISTS `mall_user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_user_address` (
  `address_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户主键id',
  `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `user_phone` varchar(11) NOT NULL DEFAULT '' COMMENT '收货人手机号',
  `default_flag` tinyint NOT NULL DEFAULT '0' COMMENT '是否为默认 0-非默认 1-是默认',
  `province_name` varchar(32) NOT NULL DEFAULT '' COMMENT '省',
  `city_name` varchar(32) NOT NULL DEFAULT '' COMMENT '城',
  `region_name` varchar(32) NOT NULL DEFAULT '' COMMENT '区',
  `area_code` varchar(32) NOT NULL DEFAULT '' COMMENT '编码',
  `detail_address` varchar(64) NOT NULL DEFAULT '' COMMENT '收件详细地址(街道/楼宇/单元)',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最新修改时间',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='收货地址表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_user_address`
--

LOCK TABLES `mall_user_address` WRITE;
/*!40000 ALTER TABLE `mall_user_address` DISABLE KEYS */;
INSERT INTO `mall_user_address` VALUES (1,4,'黎日升','13590035441',1,'吉林省','长春市','南关区','220102','吉林省 长春市 南关区 大学路1号',0,'2021-03-28 16:02:09','2021-05-06 12:49:12');
/*!40000 ALTER TABLE `mall_user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_user_role`
--

DROP TABLE IF EXISTS `mall_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_user_role` (
  `ur_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户主键id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int NOT NULL DEFAULT '0' COMMENT '创建者id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最新修改时间',
  `update_user` int DEFAULT '0' COMMENT '修改者id',
  PRIMARY KEY (`ur_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_user_role`
--

LOCK TABLES `mall_user_role` WRITE;
/*!40000 ALTER TABLE `mall_user_role` DISABLE KEYS */;
INSERT INTO `mall_user_role` VALUES (1,1,1,'2021-03-13 16:48:51',1,'2021-03-13 16:48:51',1),(2,2,2,'2021-03-13 16:50:05',2,'2021-03-13 16:50:05',2),(3,3,3,'2021-03-13 16:50:15',3,'2021-03-13 16:50:15',3),(4,4,4,'2021-03-13 16:50:26',4,'2021-03-13 16:50:26',4),(5,7,4,'2021-05-09 16:02:50',1,'2021-05-09 16:02:50',1),(6,8,4,'2021-05-09 16:40:53',1,'2021-05-09 16:40:53',1);
/*!40000 ALTER TABLE `mall_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-14  0:10:03