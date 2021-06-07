-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: mallActivity
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
-- Table structure for table `mall_flash_promotion_session`
--

DROP TABLE IF EXISTS `mall_flash_promotion_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_flash_promotion_session` (
  `mall_flash_promotion_id` bigint NOT NULL AUTO_INCREMENT COMMENT '名称',
  `name` varchar(200) DEFAULT NULL COMMENT '场次名称',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` int DEFAULT '1' COMMENT '启用状态：0->不启用；1->启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `goods_id` bigint NOT NULL COMMENT '商品id',
  `selling_price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `price` decimal(10,2) DEFAULT NULL COMMENT '限时购价格',
  `count` int DEFAULT NULL COMMENT '限时购数量',
  `selled_count` int DEFAULT '0' COMMENT '卖出的数量',
  `sort` int DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`mall_flash_promotion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='秒杀模块';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_flash_promotion_session`
--

LOCK TABLES `mall_flash_promotion_session` WRITE;
/*!40000 ALTER TABLE `mall_flash_promotion_session` DISABLE KEYS */;
INSERT INTO `mall_flash_promotion_session` VALUES (4,'双十一秒杀活动','2021-04-25 19:00:00','2021-04-30 19:00:00',1,'2021-04-26 10:47:58',3,95.00,100.00,250,0,0);
/*!40000 ALTER TABLE `mall_flash_promotion_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_home_advertise`
--

DROP TABLE IF EXISTS `mall_home_advertise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_home_advertise` (
  `mall_advertise_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `pic` varchar(500) DEFAULT NULL COMMENT '图片地址',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` int DEFAULT NULL COMMENT '上下线状态：0->下线；1->上线',
  `click_count` int DEFAULT '0' COMMENT '点击数',
  `url` varchar(500) DEFAULT NULL COMMENT '链接地址',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `sort` int DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`mall_advertise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='广告轮播';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_home_advertise`
--

LOCK TABLES `mall_home_advertise` WRITE;
/*!40000 ALTER TABLE `mall_home_advertise` DISABLE KEYS */;
INSERT INTO `mall_home_advertise` VALUES (1,'格力创造','http://mall-photo.oss-cn-beijing.aliyuncs.com/GmtmhX_1617116211525.jpg','2021-03-29 19:00:00','2021-03-30 19:00:00',1,1,'www.baidu','无',0),(2,'美的创造','http://mall-photo.oss-cn-beijing.aliyuncs.com/GmtmhX_1617116211525.jpg','2021-03-29 19:00:00','2021-03-30 19:00:00',1,0,'https://world.taobao.com/','无',0),(3,'米家广告','http://mall-photo.oss-cn-beijing.aliyuncs.com/','2021-03-17 11:00:00','2021-03-31 11:00:00',1,0,'https://world.taobao.com/','无',0),(4,'发射点发生','http://mall-photo.oss-cn-beijing.aliyuncs.com/','2021-03-17 11:00:00','2021-04-02 11:00:00',0,0,'https://world.taobao.com/','无',0);
/*!40000 ALTER TABLE `mall_home_advertise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_second_property`
--

DROP TABLE IF EXISTS `mall_second_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_second_property` (
  `mall_second_property_id` bigint NOT NULL AUTO_INCREMENT COMMENT '秒杀属性Id',
  `mall_flash_promotion_id` bigint NOT NULL COMMENT '秒杀Id',
  `goods_property_id` bigint NOT NULL COMMENT '商品属性id',
  `property_name` varchar(50) NOT NULL COMMENT '商品属性名称',
  `property_selling_price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `property_price` decimal(10,2) DEFAULT NULL COMMENT '限时购价格',
  `property_count` int DEFAULT NULL COMMENT '限时购数量',
  `property_selled_count` int DEFAULT '0' COMMENT '卖出的数量',
  `limit` int DEFAULT '1' COMMENT '每人限购数量',
  PRIMARY KEY (`mall_second_property_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='秒杀属性模块';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_second_property`
--

LOCK TABLES `mall_second_property` WRITE;
/*!40000 ALTER TABLE `mall_second_property` DISABLE KEYS */;
INSERT INTO `mall_second_property` VALUES (4,4,1,'属性名',96.00,100.00,100,0,1),(5,4,2,'属性名',195.00,200.00,150,0,1),(6,5,1,'[{\"key\":\"容量\",\"value\":\"1L\"}]',185.00,200.00,200,0,1),(7,5,2,'[{\"key\":\"容量\",\"value\":\"2L\"}]',180.00,200.00,250,0,1);
/*!40000 ALTER TABLE `mall_second_property` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-13 23:24:24