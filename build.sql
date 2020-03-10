CREATE DATABASE  IF NOT EXISTS `hotel` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hotel`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hotels`
--

DROP TABLE IF EXISTS `hotels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotels` (
  `hotel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '酒店id',
  `hotel_name` varchar(45) DEFAULT NULL COMMENT '酒店名称',
  `img_url` varchar(45) DEFAULT NULL COMMENT '酒店图片链接',
  `star_rate` varchar(45) DEFAULT NULL COMMENT '酒店星级',
  `address` varchar(45) DEFAULT NULL COMMENT '地址信息',
  `sight` varchar(45) DEFAULT NULL COMMENT '附近的地标风景信息',
  `facility` varchar(45) DEFAULT NULL COMMENT '酒店设施信息',
  `breakfast` varchar(45) DEFAULT NULL COMMENT '早餐信息',
  `description` varchar(100) DEFAULT NULL COMMENT '酒店简介',
  `total_count` int(11) DEFAULT NULL COMMENT '酒店总房间数',
  PRIMARY KEY (`hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='酒店表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotels`
--

LOCK TABLES `hotels` WRITE;
/*!40000 ALTER TABLE `hotels` DISABLE KEYS */;
INSERT INTO `hotels` VALUES (1,'洲际酒店 Intercontinental','\\img\\1.jpg','4','武侯区世纪城路','武侯祠 杜普草堂','ZZXC','ZXC','FWERE',12),(2,'半岛酒店 Peninsula Hotels','\\img\\2.jpg','3','锦江区大慈寺路','太古里','byubu','ZXC',NULL,12),(3,'卓美亚酒店 Jumirah','\\img\\3.jpg','4','成华区二环东三段','春熙路','ZZXC','ZXC',NULL,2),(4,'文华东方 Mandarin Oriental','\\img\\4.jpg','3','锦江区东安北路','熊猫基地','ZZXC','ZXC',NULL,12),(5,'安缦酒店 Aman','\\img\\5.jpg','5','武侯区青城山镇','ZXC','ZZXC','ZXC',NULL,12),(6,'悦榕庄 Bayan Tree','\\img\\6.jpg','3','成华区仙桥东三路','ZXC','ZZXC','ZXC',NULL,12),(7,'瑞吉St.Regis','\\img\\7.jpg','5','青羊区太升南路','ZXC','ZZXC','ZXC',NULL,12),(8,'四季酒店Four Seasons','\\img\\8.jpg','2','郫都区犀湖街','ZXC','ZZXC','ZXC',NULL,12),(9,'丽兹·卡尔顿 Ritz-Carlton','\\img\\9.jpg','4','青羊区顺城大街','ZXC','ZZXC','ZXC',NULL,112),(10,'喜来登 ','\\img\\10.jpg','3','ZXC','ZXC','ZZXC','ZXC',NULL,12),(11,'WER','ASD','wuxingji','ZXC','ZXC','ZZXC','ZXC',NULL,12),(12,'WER','ASD','QWD','ZXC','ZXC','ZZXC','ZXC',NULL,12),(13,'WER','yongchi','QWD','ZXC','ZXC','ZZXC','ZXC',NULL,12),(14,'WER','ASD','QWD','ZXC','ZXC','chengdu','ZXC',NULL,12);
/*!40000 ALTER TABLE `hotels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `managers`
--

DROP TABLE IF EXISTS `managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `managers` (
  `manager_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `manager_password` varchar(16) DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `managers`
--

LOCK TABLES `managers` WRITE;
/*!40000 ALTER TABLE `managers` DISABLE KEYS */;
/*!40000 ALTER TABLE `managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `room_id` int(11) DEFAULT NULL COMMENT '房型id',
  `hotel_id` int(11) DEFAULT NULL COMMENT '酒店id',
  `state` varchar(2) DEFAULT NULL COMMENT '订单状态',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `payment` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rates`
--

DROP TABLE IF EXISTS `rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rates` (
  `rate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `hotel_id` int(11) DEFAULT NULL COMMENT '酒店id',
  `comment` varchar(45) DEFAULT NULL COMMENT '用户评论',
  PRIMARY KEY (`rate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评价表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rates`
--

LOCK TABLES `rates` WRITE;
/*!40000 ALTER TABLE `rates` DISABLE KEYS */;
/*!40000 ALTER TABLE `rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `rooms_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房型id',
  `hotel_id` int(11) DEFAULT NULL COMMENT '所属酒店id',
  `price` float DEFAULT NULL COMMENT '房型价格',
  `type` varchar(5) DEFAULT NULL COMMENT '类型',
  `size` float DEFAULT NULL COMMENT '房间大小',
  `facility` varchar(60) DEFAULT NULL COMMENT '房间设施',
  `count` int(11) DEFAULT NULL COMMENT '剩余房间数',
  PRIMARY KEY (`rooms_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='房型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,3,123,'豪华',100,'洗衣机',12);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenants`
--

DROP TABLE IF EXISTS `tenants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenants` (
  `tenant_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '住客id',
  `room_id` int(11) DEFAULT NULL COMMENT '房型id',
  `hotel_id` int(11) DEFAULT NULL COMMENT '酒店id',
  `user_id` int(11) DEFAULT NULL COMMENT '客户id',
  PRIMARY KEY (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='住客表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenants`
--

LOCK TABLES `tenants` WRITE;
/*!40000 ALTER TABLE `tenants` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(45) NOT NULL COMMENT '密码',
  `salt` varchar(10) NOT NULL COMMENT 'MD5校验使用的salt',
  `email` varchar(20) DEFAULT NULL COMMENT '邮件',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `real_name` varchar(10) DEFAULT NULL COMMENT '用户真名',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `certificate_type` varchar(10) DEFAULT NULL COMMENT '证件类型',
  `certificate_number` varchar(20) DEFAULT NULL COMMENT '证件号码',
  `sex` varchar(5) DEFAULT NULL,
  `pw_question` varchar(45) DEFAULT NULL,
  `pw_answer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'zhou','f6d9fdc9a5680017a589e1decbf42fd1','TAuHWS','34298796@qq.com','13219058093',NULL,NULL,NULL,NULL,'男','您的总裁是谁','丰哥'),(2,'zhous','b5686084e8d44de58d1d4ab9d49e4016','hhBJba',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'zhouA','6b23e30b1f24e62f3fb5c69988e1188b','FYkoLs',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-10 14:41:36
