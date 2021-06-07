-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: mallOrder
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
-- Table structure for table `mall_company_address`
--

DROP TABLE IF EXISTS `mall_company_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_company_address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address_name` varchar(200) DEFAULT '' COMMENT '地址名称',
  `send_status` int DEFAULT '0' COMMENT '默认发货地址：0->否；1->是',
  `receive_status` int DEFAULT '0' COMMENT '是否默认收货地址：0->否；1->是',
  `name` varchar(64) DEFAULT NULL COMMENT '公司名称',
  `phone` varchar(64) DEFAULT NULL COMMENT '公司电话',
  `province` varchar(64) DEFAULT NULL COMMENT '省/直辖市',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `region` varchar(64) DEFAULT NULL COMMENT '区',
  `detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='公司地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_company_address`
--

LOCK TABLES `mall_company_address` WRITE;
/*!40000 ALTER TABLE `mall_company_address` DISABLE KEYS */;
INSERT INTO `mall_company_address` VALUES (1,'',1,1,'东莞理工科技有限公司','13590035440','广东省','深圳市','宝安区','广东省深圳市宝安区大学路2号'),(2,'',0,0,'卧安科技有限公司','1359003440','广东省','深圳市','福田区','广东省深圳市福田区大学路1号');
/*!40000 ALTER TABLE `mall_company_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_coupon`
--

DROP TABLE IF EXISTS `mall_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_coupon` (
  `mall_coupon_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `count` int DEFAULT '10' COMMENT '数量',
  `amount` decimal(10,2) DEFAULT '20.00' COMMENT '金额',
  `per_limit` int DEFAULT '1' COMMENT '每人限领张数',
  `min_point` decimal(10,2) DEFAULT '0.00' COMMENT '使用门槛；0表示无门槛',
  `start_time` datetime DEFAULT NULL COMMENT '开始使用时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束使用时间',
  `note` varchar(200) DEFAULT '' COMMENT '备注',
  `receive_count` int DEFAULT '0' COMMENT '领取数量',
  `use_count` int DEFAULT '0' COMMENT '已使用数量',
  `enable_time` datetime DEFAULT NULL COMMENT '可以领取的日期',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最新修改时间',
  PRIMARY KEY (`mall_coupon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='优惠劵';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_coupon`
--

LOCK TABLES `mall_coupon` WRITE;
/*!40000 ALTER TABLE `mall_coupon` DISABLE KEYS */;
INSERT INTO `mall_coupon` VALUES (1,'全场20元优惠卷',49,20.00,2,100.00,'2021-04-09 11:00:00','2021-04-26 11:00:00','双十一强势来袭',1,0,NULL,'2021-03-28 02:08:57','2021-05-13 15:56:54'),(3,'优惠20元',99,20.00,1,0.00,'2021-03-23 11:00:00','2021-03-29 11:00:00','全场优惠',1,0,NULL,'2021-03-28 16:25:26','2021-03-28 16:50:28'),(4,'优惠50元',99,50.00,1,10000.00,'2021-03-24 11:00:00','2021-03-29 11:00:00','全场优惠',1,0,NULL,'2021-03-28 16:26:18','2021-03-28 16:50:31'),(5,'优惠卷20元起',99,20.00,1,100.00,'2021-03-28 11:00:00','2021-03-29 11:00:00','全场优惠',1,0,NULL,'2021-03-28 16:27:17','2021-03-28 16:50:33'),(6,'优惠卷20',199,20.00,1,20.00,'2021-03-14 11:00:00','2021-03-23 11:00:00','过期优惠卷',1,0,NULL,'2021-03-28 16:28:19','2021-03-28 16:50:36'),(7,'全场20元优惠卷',48,20.00,1,0.00,'2021-03-31 11:00:00','2021-07-28 11:00:00','双十一强势来袭',2,1,NULL,'2021-04-05 20:19:20','2021-05-07 12:31:37');
/*!40000 ALTER TABLE `mall_coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_coupon_history`
--

DROP TABLE IF EXISTS `mall_coupon_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_coupon_history` (
  `coupon_history_id` bigint NOT NULL AUTO_INCREMENT,
  `coupon_id` bigint DEFAULT NULL COMMENT '优惠券id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `order_id` bigint DEFAULT NULL COMMENT '订单id',
  `member_nickname` varchar(64) DEFAULT NULL COMMENT '领取人昵称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `use_status` int DEFAULT NULL COMMENT '使用状态：0->未使用；1->已使用；2->已过期',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `order_sn` varchar(100) DEFAULT NULL COMMENT '订单号码',
  PRIMARY KEY (`coupon_history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户领取的优惠劵记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_coupon_history`
--

LOCK TABLES `mall_coupon_history` WRITE;
/*!40000 ALTER TABLE `mall_coupon_history` DISABLE KEYS */;
INSERT INTO `mall_coupon_history` VALUES (1,1,4,1,'4别名','2021-03-28 16:47:41',1,'2021-03-27 08:52:00','25698746665'),(2,3,4,NULL,'4别名','2021-03-28 16:50:28',2,NULL,NULL),(3,4,4,NULL,'4别名','2021-03-28 16:50:31',2,NULL,NULL),(4,5,4,NULL,'4别名','2021-03-28 16:50:33',2,NULL,NULL),(5,6,4,NULL,'4别名','2021-03-28 16:50:36',2,NULL,NULL),(6,7,4,6,'4别名','2021-04-05 20:19:45',1,'2021-04-15 11:38:46','2021041600384316188735413465599'),(7,7,4,NULL,'user','2021-04-26 20:37:52',2,NULL,NULL);
/*!40000 ALTER TABLE `mall_coupon_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_delivery_logistics`
--

DROP TABLE IF EXISTS `mall_delivery_logistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_delivery_logistics` (
  `delivery_logistics_id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL COMMENT '订单id',
  `delivery_logistics_no` varchar(100) NOT NULL DEFAULT '' COMMENT '物流单号',
  `delivery_company_name` varchar(30) NOT NULL DEFAULT '' COMMENT '物流公司名',
  `company_address_id` bigint DEFAULT NULL COMMENT '公司地址id',
  `order_address_id` bigint DEFAULT NULL COMMENT '订单地址id',
  `operation_name` varchar(30) NOT NULL COMMENT '操作人：用户；系统；后台管理员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`delivery_logistics_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='物流单号';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_delivery_logistics`
--

LOCK TABLES `mall_delivery_logistics` WRITE;
/*!40000 ALTER TABLE `mall_delivery_logistics` DISABLE KEYS */;
INSERT INTO `mall_delivery_logistics` VALUES (2,3,'2021040420391559521481125779682','顺丰快递',2,3,'admin','2021-04-04 20:39:18'),(3,8,'2021041711510241257292182698921','顺丰快递',2,8,'admin','2021-04-17 11:51:03'),(4,12,'2021050917371212869483782477648','中通快递',1,12,'admin','2021-05-09 17:37:12');
/*!40000 ALTER TABLE `mall_delivery_logistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_order`
--

DROP TABLE IF EXISTS `mall_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_order` (
  `order_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单表主键id',
  `order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '订单号',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户主键id',
  `total_amount` decimal(10,2) NOT NULL DEFAULT '1.00' COMMENT '订单总金额',
  `pay_status` tinyint NOT NULL DEFAULT '0' COMMENT '支付状态:0.未支付,1.支付成功,支付失败',
  `pay_type` tinyint NOT NULL DEFAULT '0' COMMENT '0.无 1.支付宝支付 2.微信支付',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `order_status` tinyint NOT NULL DEFAULT '0' COMMENT '订单状态:0.待支付 1.待发货 2.已发货 3:交易成功 4.退货中 5 退货完成 -1.手动关闭 -2.超时关闭 -3.商家关闭',
  `extra_info` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最新修改时间',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
  `freight_amount` decimal(10,2) DEFAULT '0.00' COMMENT '运费金额',
  `coupon_amount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠券抵扣金额',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '确认收货时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_order`
--

LOCK TABLES `mall_order` WRITE;
/*!40000 ALTER TABLE `mall_order` DISABLE KEYS */;
INSERT INTO `mall_order` VALUES (3,'2021032920361204685016747871687',4,1600.00,0,1,'2021-04-04 06:59:57',3,'紧急送到',0,'2021-03-29 20:36:12','2021-04-04 20:45:24',1580.00,0.00,20.00,'2021-04-04 07:39:16','2021-04-04 07:45:23'),(6,'2021041600384316188735413465599',4,200.00,1,1,'2021-04-15 11:38:40',5,'',0,'2021-04-16 00:38:43','2021-04-28 20:37:38',180.00,0.00,20.00,NULL,NULL),(8,'2021041610281782383604357019252',4,200.00,0,1,'2021-04-16 03:23:19',3,'',0,'2021-04-16 10:28:17','2021-04-17 23:44:47',200.00,0.00,0.00,'2021-04-16 22:51:02','2021-04-17 10:44:47'),(9,'2021041812305621683059852977024',4,200.00,0,1,'2021-04-17 23:31:08',1,'',0,'2021-04-18 12:30:56','2021-04-18 12:31:08',200.00,0.00,0.00,NULL,NULL),(12,'2021050713013765588484488775280',4,200.00,1,0,'2021-05-07 00:01:36',3,'',0,'2021-05-07 13:01:37','2021-05-09 17:45:59',200.00,0.00,0.00,'2021-05-09 04:37:12','2021-05-09 04:45:59'),(14,'2021050914460107882570773963817',4,1379.00,1,0,'2021-05-09 01:46:01',4,'',0,'2021-05-09 14:46:01','2021-05-09 17:48:00',1379.00,0.00,0.00,NULL,NULL),(15,'2021050917492936085058791199668',4,9652.00,1,1,'2021-05-09 04:49:29',1,'',0,'2021-05-09 17:49:29','2021-05-09 17:49:29',9652.00,0.00,0.00,NULL,NULL),(16,'2021050917503124783105455330190',4,9652.00,0,1,'2021-05-09 04:50:55',1,'',0,'2021-05-09 17:50:31','2021-05-09 17:50:56',9652.00,0.00,0.00,NULL,NULL),(17,'2021051010131388384047811271087',4,6276.00,1,1,'2021-05-09 21:13:13',1,'',0,'2021-05-10 10:13:13','2021-05-10 10:13:13',6276.00,0.00,0.00,NULL,NULL);
/*!40000 ALTER TABLE `mall_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_order_address`
--

DROP TABLE IF EXISTS `mall_order_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_order_address` (
  `order_id` bigint NOT NULL COMMENT '订单表地址id',
  `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `user_phone` varchar(11) NOT NULL DEFAULT '' COMMENT '收货人手机号',
  `province_name` varchar(32) NOT NULL DEFAULT '' COMMENT '省',
  `city_name` varchar(32) NOT NULL DEFAULT '' COMMENT '城',
  `region_name` varchar(32) NOT NULL DEFAULT '' COMMENT '区',
  `detail_address` varchar(64) NOT NULL DEFAULT '' COMMENT '收件详细地址(街道/楼宇/单元)',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单收货地址关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_order_address`
--

LOCK TABLES `mall_order_address` WRITE;
/*!40000 ALTER TABLE `mall_order_address` DISABLE KEYS */;
INSERT INTO `mall_order_address` VALUES (3,'黎日升','13590035440','吉林省','长春市','南关区','吉林省长春市南关区大学路1号'),(6,'黎日升','13590035440','吉林省','长春市','南关区','吉林省长春市南关区大学路1号'),(8,'黎日升','13590035440','吉林省','长春市','南关区','吉林省长春市南关区大学路1号'),(9,'黎日升','13590035440','吉林省','长春市','南关区','吉林省长春市南关区大学路1号'),(12,'黎日升','13590035441','吉林省','长春市','南关区','吉林省 长 春市 南关区 大学路1号'),(14,'黎日升','13590035441','吉林省','长春市','南关区','吉林省 长春市 南关区 大学路1号'),(15,'黎日升','13590035441','吉林省','长春市','南关区','吉林省 长春市 南关区 大学路1号'),(16,'黎日升','13590035441','吉林省','长春市','南关区','吉林省 长春市 南关区 大学路1号'),(17,' 黎日升','13590035441','吉林省','长春市','南关区','吉林省 长春市 南关区 大学路1号');
/*!40000 ALTER TABLE `mall_order_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_order_item`
--

DROP TABLE IF EXISTS `mall_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_order_item` (
  `order_item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单关联购物项主键id',
  `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单主键id',
  `goods_property_id` bigint NOT NULL DEFAULT '0' COMMENT '关联商品参数id',
  `goods_name` varchar(200) NOT NULL DEFAULT '' COMMENT '下单时商品的名称(订单快照)',
  `goods_info` varchar(200) NOT NULL DEFAULT '' COMMENT '下单时商品的简介(订单快照)',
  `goods_cover_img` varchar(200) NOT NULL DEFAULT '' COMMENT '下单时商品的主图(订单快照)',
  `selling_price` decimal(10,2) NOT NULL DEFAULT '1.00' COMMENT '下单时商品的价格(订单快照)',
  `goods_count` int NOT NULL DEFAULT '1' COMMENT '数量(订单快照)',
  `goods_total_price` decimal(10,2) NOT NULL COMMENT '订单总价(订单快照)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='订单详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_order_item`
--

LOCK TABLES `mall_order_item` WRITE;
/*!40000 ALTER TABLE `mall_order_item` DISABLE KEYS */;
INSERT INTO `mall_order_item` VALUES (3,3,1,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',200.00,4,800.00,'2021-03-29 20:36:12'),(4,3,1,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',200.00,4,800.00,'2021-03-29 20:36:12'),(7,6,2,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"2L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',200.00,1,200.00,'2021-04-16 00:38:43'),(9,8,1,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',200.00,1,200.00,'2021-04-16 10:28:18'),(10,9,2,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"2L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',200.00,1,200.00,'2021-04-18 12:30:56'),(13,12,1,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',200.00,1,200.00,'2021-05-07 13:01:37'),(15,14,14,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1379.00,1,1379.00,'2021-05-09 14:46:01'),(16,15,22,'电饭锅大容量10升15-20人食堂酒店商用老式大电饭煲不粘锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%852.jpg',9652.00,1,9652.00,'2021-05-09 17:49:29'),(17,16,22,'电饭锅大容量10升15-20人食堂酒店商用老式大电饭煲不粘锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%852.jpg',9652.00,1,9652.00,'2021-05-09 17:50:31'),(18,17,6,'电饭煲家用4L多功能智能大容量迷你电饭锅煮饭官方旗舰店正品','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%854.jpg',6276.00,1,6276.00,'2021-05-10 10:13:17');
/*!40000 ALTER TABLE `mall_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_order_operate_history`
--

DROP TABLE IF EXISTS `mall_order_operate_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_order_operate_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL COMMENT '订单id',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人：用户；系统；后台管理员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `order_status` int DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员操作订单的日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_order_operate_history`
--

LOCK TABLES `mall_order_operate_history` WRITE;
/*!40000 ALTER TABLE `mall_order_operate_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `mall_order_operate_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_order_return_apply`
--

DROP TABLE IF EXISTS `mall_order_return_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_order_return_apply` (
  `order_return_apply_id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL COMMENT '订单id',
  `company_address_id` bigint DEFAULT '0' COMMENT '收货地址表id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `return_amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  `return_name` varchar(100) DEFAULT NULL COMMENT '退货人姓名',
  `return_phone` varchar(100) DEFAULT NULL COMMENT '退货人电话',
  `status` int DEFAULT '0' COMMENT '申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `handle_note` varchar(500) DEFAULT '' COMMENT '处理备注',
  `handle_man` varchar(100) DEFAULT '' COMMENT '处理人员',
  `receive_man` varchar(100) DEFAULT '' COMMENT '收货人',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `receive_note` varchar(500) DEFAULT '' COMMENT '收货备注',
  PRIMARY KEY (`order_return_apply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='退货记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_order_return_apply`
--

LOCK TABLES `mall_order_return_apply` WRITE;
/*!40000 ALTER TABLE `mall_order_return_apply` DISABLE KEYS */;
INSERT INTO `mall_order_return_apply` VALUES (1,4,1,'2021-04-03 10:08:55',1580.00,'user','13590035440',2,'2021-04-04 03:43:03','','通过退货','','','2021-04-04 04:09:06','收货成功'),(6,6,2,'2021-04-18 10:25:45',180.00,'发生的','13590035440',2,'2021-04-17 21:30:47','不想买了','同意退货','','','2021-04-17 22:39:03','确认收到货'),(7,9,NULL,'2021-05-07 13:02:37',200.00,'黎日升','13590035440',0,NULL,'不想买啦','','','',NULL,''),(8,14,2,'2021-05-09 17:46:29',1379.00,'黎日升','13590035441',1,'2021-05-09 04:47:04','不合适','同意退货','','',NULL,'');
/*!40000 ALTER TABLE `mall_order_return_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_shopping_cart_item`
--

DROP TABLE IF EXISTS `mall_shopping_cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mall_shopping_cart_item` (
  `cart_item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物项主键id',
  `user_id` bigint NOT NULL COMMENT '用户主键id',
  `goods_property_id` bigint NOT NULL DEFAULT '0' COMMENT '关联商品属性id',
  `goods_name` varchar(200) NOT NULL DEFAULT '' COMMENT '选中时商品的名称',
  `goods_info` varchar(100) NOT NULL DEFAULT '' COMMENT '选中商品简介',
  `goods_cover_img` varchar(200) NOT NULL DEFAULT '' COMMENT '下单时商品的主图',
  `goods_count` int NOT NULL DEFAULT '1' COMMENT '数量(最大为5)',
  `goods_price` decimal(10,2) NOT NULL DEFAULT '1.00' COMMENT '商品价格',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最新修改时间',
  PRIMARY KEY (`cart_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='购物车';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_shopping_cart_item`
--

LOCK TABLES `mall_shopping_cart_item` WRITE;
/*!40000 ALTER TABLE `mall_shopping_cart_item` DISABLE KEYS */;
INSERT INTO `mall_shopping_cart_item` VALUES (1,4,1,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','',4,200.00,1,'2021-03-27 22:42:09','2021-03-29 20:36:14'),(2,4,1,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','',3,200.00,1,'2021-03-28 01:12:31','2021-03-28 01:12:58'),(3,4,1,'热 卖电饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','',4,200.00,1,'2021-03-28 20:13:43','2021-03-29 20:36:14'),(4,4,1,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',2,200.00,1,'2021-04-15 15:45:47','2021-04-15 22:07:38'),(5,4,2,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"2L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',1,200.00,1,'2021-04-15 18:52:03','2021-04-18 12:30:57'),(6,4,1,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',1,200.00,1,'2021-04-15 20:40:59','2021-04-15 22:10:35'),(7,4,1,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',2,200.00,1,'2021-04-15 22:11:43','2021-04-15 22:16:57'),(8,4,2,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"2L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',2,200.00,1,'2021-04-15 22:11:47','2021-04-15 22:12:29'),(9,4,2,'热卖电 饭锅','[{\"key\":\"容量\",\"value\":\"2L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',1,200.00,1,'2021-04-15 22:17:16','2021-04-16 00:38:46'),(10,4,1,'热卖电饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','http://mall-photo.oss-cn-beijing.aliyuncs.com/dianfangguo1.jpg',1,200.00,1,'2021-04-26 21:23:17','2021-05-07 13:01:39'),(11,4,14,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,1379.00,1,'2021-04-28 17:00:53','2021-04-28 19:44:21'),(12,4,14,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,1379.00,1,'2021-04-28 17:01:05','2021-05-09 14:46:02'),(13,4,64,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功 能定时预约','[{\"key\":\"容量\",\"value\":\"2L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,3121.00,1,'2021-04-28 20:00:52','2021-05-04 17:46:47'),(14,4,14,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,1379.00,1,'2021-05-04 17:46:34','2021-05-04 17:46:47'),(15,4,14,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,1379.00,1,'2021-05-04 17:46:34','2021-05-04 17:46:47'),(16,4,14,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',2,1379.00,1,'2021-05-04 17:46:34','2021-05-09 17:50:07'),(17,4,14,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,1379.00,1,'2021-05-05 16:16:50','2021-05-05 17:23:17'),(18,4,14,'西哲伊贺烧 电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,1379.00,1,'2021-05-05 17:22:50','2021-05-09 17:50:07'),(19,4,14,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,1379.00,1,'2021-05-05 17:24:52','2021-05-09 17:50:07'),(20,4,14,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,1379.00,1,'2021-05-05 17:29:59','2021-05-09 17:50:07'),(21,4,14,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,1379.00,0,'2021-05-05 17:30:54','2021-05-05 17:30:54'),(22,4,22,'电饭锅大容量10升15-20人食堂酒店商用老式大电饭煲不粘锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%852.jpg',1,9652.00,1,'2021-05-05 17:32:31','2021-05-09 17:49:30'),(23,4,4,'电饭煲家用智能多功能预约迷你小电饭锅单人小型宿舍1-2人','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%852.jpg',1,6739.00,0,'2021-05-05 17:32:37','2021-05-05 17:32:37'),(24,4,34,'电饭煲家用4L升智能多功能蒸煮 粥电饭锅官方旗舰店正品','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%855.jpg',1,9361.00,1,'2021-05-05 17:32:43','2021-05-09 17:50:07'),(25,4,33,'电饭煲5L升智能家用多功能饭锅3大容量4-8人官方旗舰店正品','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,4977.00,1,'2021-05-05 17:32:50','2021-05-09 17:50:07'),(26,4,36,'食色低糖电饭煲米汤分离4l智能家用多功能去无糖蒸煮沥米饭锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%851.jpg',1,1277.00,1,'2021-05-05 17:32:56','2021-05-09 17:50:07'),(27,4,28,'电饭煲家用3L升米饭锅小2人智能4多功能5-6官方旗舰店正品','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,7026.00,1,'2021-05-05 17:33:06','2021-05-09 17:50:07'),(28,4,27,'电饭煲5L升4智能家用多功能电饭锅大容量6人用蒸米煮饭正品','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%852.jpg',1,9896.00,1,'2021-05-05 17:33:12','2021-05-09 17:50:07'),(29,4,6,'电饭煲家用4L多功能智能大容量迷你电饭锅煮饭官方旗舰店正品','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%854.jpg',1,6276.00,1,'2021-05-05 17:33:17','2021-05-10 10:13:19'),(30,4,22,'电饭锅大容量10升15-20人食堂酒 店商用老式大电饭煲不粘锅','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%852.jpg',1,9652.00,1,'2021-05-09 17:48:15','2021-05-09 17:50:32'),(31,4,14,'西哲伊贺烧电饭锅煲土陶瓷内胆智能家用多功能定时预约','[{\"key\":\"容量\",\"value\":\"1L\"}]','https://mall-photo.oss-cn-beijing.aliyuncs.com/%E7%94%B5%E9%A5%AD%E9%94%853.jpg',1,1379.00,1,'2021-05-10 10:10:54','2021-05-10 10:11:46');
/*!40000 ALTER TABLE `mall_shopping_cart_item` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
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

-- Dump completed on 2021-05-14  0:06:19