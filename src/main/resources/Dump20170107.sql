CREATE DATABASE  IF NOT EXISTS `mahjong_back` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mahjong_back`;
-- MySQL dump 10.13  Distrib 5.5.53, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	5.5.53-0ubuntu0.14.04.1

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
-- Table structure for table `agent_recharge_info`
--

DROP TABLE IF EXISTS `agent_recharge_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_recharge_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `recharge_num` int(11) NOT NULL,
  `send_num` int(11) NOT NULL,
  `recharge_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent_recharge_info`
--

LOCK TABLES `agent_recharge_info` WRITE;
/*!40000 ALTER TABLE `agent_recharge_info` DISABLE KEYS */;
INSERT INTO `agent_recharge_info` VALUES (1,5,20,1,8,'2017-01-06 22:46:49'),(2,5,20,1,8,'2017-01-06 22:46:49'),(3,5,20,1,8,'2017-01-06 22:46:49'),(4,5,20,1,1,'2017-01-06 22:46:49'),(5,5,20,1,1,'2017-01-06 22:46:49'),(6,5,20,1,8,'2017-01-06 22:46:49'),(7,5,20,1,1,'2017-01-06 22:46:49'),(8,5,20,1,1,'2017-01-06 22:46:49'),(9,5,20,1,1,'2017-01-06 22:46:49'),(10,5,20,1,1,'2017-01-06 22:46:49'),(11,5,50,0,8,'2017-01-07 15:16:26'),(12,5,50,0,8,'2017-01-07 15:16:52');
/*!40000 ALTER TABLE `agent_recharge_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `url` varchar(45) DEFAULT NULL,
  `level` int(11) NOT NULL,
  `menu_level` int(11) NOT NULL DEFAULT '0',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'会员','',99,1,0),(2,'会员列表','/admin/list',99,2,1),(3,'代理商','',6,1,0),(4,'我的购卡','/agent/my/buyed',6,2,3),(5,'我的代理商','/agent//myAgentList',6,2,3),(6,'我的充值','/agent/my/recharge',6,2,3),(7,'玩家充值','/agent/playerRecharge/playerInfo',6,2,3),(8,'代理商推荐','/agent/recommend',6,2,3);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `level` int(11) NOT NULL,
  `url_prefix` varchar(45) NOT NULL DEFAULT '0',
  `desc` varchar(45) NOT NULL,
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (6,'agent','代理商'),(99,'admin','管理员');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_recharge_info`
--

DROP TABLE IF EXISTS `player_recharge_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player_recharge_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `recharge_num` int(11) DEFAULT NULL,
  `send_num` int(11) DEFAULT NULL,
  `recharge_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_recharge_info`
--

LOCK TABLES `player_recharge_info` WRITE;
/*!40000 ALTER TABLE `player_recharge_info` DISABLE KEYS */;
INSERT INTO `player_recharge_info` VALUES (1,1111,1211,100,1,'2016-12-31 23:58:30'),(2,1112,71118,200,2,'2017-01-04 11:38:03'),(3,1112,71118,200,20,'2017-01-04 11:41:32'),(4,1112,71118,100,10,'2017-01-04 11:41:32'),(5,1112,71118,100,10,'2017-01-04 11:41:32'),(6,1112,71118,200,20,'2017-01-04 11:41:32'),(7,1112,71118,500,50,'2017-01-04 11:41:32'),(8,1112,71118,200,20,'2017-01-04 11:41:32'),(9,1112,71118,200,20,'2017-01-04 11:41:32'),(10,1112,71118,200,20,'2017-01-04 11:41:32'),(11,1112,71118,200,20,'2017-01-04 11:41:32'),(12,1112,71118,100,10,'2017-01-04 11:41:32'),(13,1112,71118,200,20,'2017-01-04 11:41:32'),(14,1112,71118,200,20,'2017-01-04 11:41:32'),(15,1112,71118,100,10,'2017-01-04 11:41:32'),(16,1112,71118,200,20,'2017-01-04 11:41:32'),(17,1112,71118,500,50,'2017-01-04 11:41:32'),(18,1112,71118,200,20,'2017-01-04 11:41:32'),(19,1112,71118,500,50,'2017-01-04 11:41:32'),(20,1112,71118,500,50,'2017-01-04 11:41:32'),(21,1112,71118,100,10,'2017-01-04 11:41:32'),(22,1112,71118,100,10,'2017-01-04 11:41:32'),(23,1112,71118,200,20,'2017-01-04 11:41:32'),(24,1112,71118,500,50,'2017-01-04 11:41:32'),(25,1112,71118,100,10,'2017-01-04 11:41:32'),(26,1112,71118,100,10,'2017-01-04 11:41:32'),(27,1112,71118,500,50,'2017-01-04 11:41:32'),(28,1112,71118,100,10,'2017-01-04 11:41:32'),(29,1112,71118,200,20,'2017-01-04 11:41:32'),(30,1112,71118,200,20,'2017-01-04 11:41:32'),(31,1112,71118,100,10,'2017-01-04 11:41:32'),(32,1112,71118,500,50,'2017-01-04 11:41:32'),(33,1112,71118,100,10,'2017-01-04 11:41:32'),(34,1112,71118,500,50,'2017-01-04 11:41:33'),(35,1112,71118,100,10,'2017-01-04 11:41:33'),(36,1112,71118,500,50,'2017-01-04 11:41:33'),(37,1112,71118,100,10,'2017-01-04 11:41:33'),(38,1112,71118,500,50,'2017-01-04 11:41:33'),(39,1112,71118,200,20,'2017-01-04 11:41:33'),(40,1112,71118,500,50,'2017-01-04 11:41:33'),(41,1112,71118,500,50,'2017-01-04 11:41:33'),(42,1112,71118,200,20,'2017-01-04 11:41:33'),(43,1112,71118,100,10,'2017-01-04 11:41:33'),(44,1112,71118,200,20,'2017-01-04 11:41:33'),(45,1112,71118,100,10,'2017-01-04 11:41:33'),(46,1112,71118,100,10,'2017-01-04 11:41:33'),(47,1112,71118,500,50,'2017-01-04 11:41:33'),(48,1112,71118,100,10,'2017-01-04 11:41:33'),(49,1112,71118,500,50,'2017-01-04 11:41:33'),(50,1112,71118,500,50,'2017-01-04 11:41:33'),(51,1112,71118,100,10,'2017-01-04 11:41:33'),(52,1112,71118,100,10,'2017-01-04 11:41:33'),(53,1112,71118,200,20,'2017-01-04 11:41:33'),(54,1112,71118,200,20,'2017-01-04 11:41:33'),(55,1112,71118,500,50,'2017-01-04 11:41:33'),(56,1112,71118,200,20,'2017-01-04 11:41:33'),(57,1112,71118,500,50,'2017-01-04 11:41:33'),(58,1112,71118,100,10,'2017-01-04 11:41:33'),(59,1112,71118,200,20,'2017-01-04 11:41:33'),(60,1112,71118,500,50,'2017-01-04 11:41:33'),(61,1112,71118,500,50,'2017-01-04 11:41:33'),(62,1112,71118,100,10,'2017-01-04 11:41:33'),(63,1112,71118,100,10,'2017-01-04 11:41:33'),(64,1112,71118,100,10,'2017-01-04 11:41:33'),(65,1112,71118,200,20,'2017-01-04 11:41:33'),(66,1112,71118,500,50,'2017-01-04 11:41:33'),(67,1112,71118,200,20,'2017-01-04 11:41:33'),(68,1112,71118,200,20,'2017-01-04 11:41:33'),(69,1112,71118,100,10,'2017-01-04 11:41:33'),(70,1112,71118,100,10,'2017-01-04 11:41:33'),(71,1112,71118,200,20,'2017-01-04 11:41:33'),(72,1112,71118,200,20,'2017-01-04 11:41:33'),(73,1112,71118,100,10,'2017-01-04 11:41:33'),(74,1112,71118,500,50,'2017-01-04 11:41:33'),(75,1112,71118,200,20,'2017-01-04 11:41:33'),(76,1112,71118,100,10,'2017-01-04 11:41:33'),(77,1112,71118,500,50,'2017-01-04 11:41:33'),(78,1112,71118,500,50,'2017-01-04 11:41:33'),(79,1112,71118,500,50,'2017-01-04 11:41:33'),(80,1112,71118,200,20,'2017-01-04 11:41:33'),(81,1112,71118,500,50,'2017-01-04 11:41:33'),(82,1112,71118,500,50,'2017-01-04 11:41:33'),(83,1112,71118,200,20,'2017-01-04 11:41:33'),(84,1112,71118,200,20,'2017-01-04 11:41:33'),(85,1112,71118,200,20,'2017-01-04 11:41:33'),(86,1112,71118,200,20,'2017-01-04 11:41:33'),(87,1112,71118,500,50,'2017-01-04 11:41:33'),(88,1112,71118,100,10,'2017-01-04 11:41:33'),(89,1112,71118,500,50,'2017-01-04 11:41:33'),(90,1112,71118,100,10,'2017-01-04 11:41:33'),(91,1112,71118,200,20,'2017-01-04 11:41:33'),(92,1112,71118,200,20,'2017-01-04 11:41:33'),(93,1112,71118,200,20,'2017-01-04 11:41:33'),(94,1112,71118,100,10,'2017-01-04 11:41:33'),(95,1112,71118,100,10,'2017-01-04 11:41:33'),(96,1112,71118,100,10,'2017-01-04 11:41:33'),(97,1112,71118,100,10,'2017-01-04 11:41:33'),(98,1112,71118,100,10,'2017-01-04 11:41:33'),(99,1112,71118,100,10,'2017-01-04 11:41:33'),(100,1112,71118,100,10,'2017-01-04 11:41:33'),(101,1112,71118,100,10,'2017-01-04 11:41:33'),(102,1112,71118,100,10,'2017-01-04 11:41:33'),(103,1112,5,1,0,'2017-01-05 22:12:03'),(104,1111,5,1,0,'2017-01-05 22:14:27'),(105,1111,5,2,0,'2017-01-05 22:15:28'),(106,1111,5,2,0,'2017-01-05 22:15:31'),(107,1111,5,2,0,'2017-01-05 22:16:28'),(108,1111,5,2,0,'2017-01-05 22:16:29'),(109,1111,1,1,0,'2017-01-05 22:56:41'),(110,1111,1,1,0,'2017-01-06 21:15:04');
/*!40000 ALTER TABLE `player_recharge_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recharge_send`
--

DROP TABLE IF EXISTS `recharge_send`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recharge_send` (
  `level` int(11) NOT NULL,
  `recharge_num` int(11) NOT NULL,
  `return_num` int(11) NOT NULL,
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recharge_send`
--

LOCK TABLES `recharge_send` WRITE;
/*!40000 ALTER TABLE `recharge_send` DISABLE KEYS */;
INSERT INTO `recharge_send` VALUES (6,100,1),(7,100,7),(8,100,8),(9,100,9),(10,100,10);
/*!40000 ALTER TABLE `recharge_send` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `level` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `parent_user` int(11) DEFAULT NULL,
  `wechart` varchar(45) NOT NULL,
  `mobile` bigint(45) NOT NULL,
  `diamond` int(11) NOT NULL DEFAULT '0',
  `integral` int(11) NOT NULL DEFAULT '0',
  `province` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `county` varchar(45) DEFAULT NULL,
  `detailed_address` varchar(45) DEFAULT NULL,
  `agent_num` int(45) NOT NULL,
  `key_code` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'%E4%B8%80%E4%B8%AA','xxxxxx',99,1,NULL,'',0,0,0,'',NULL,NULL,NULL,0,''),(2,'%E4%B8%80%E4%B8%AA','xxxxxx',6,0,0,'cccc',2147483647,0,0,'	%E7%9C%81%E4','','','',0,'afsfsaf'),(3,'%E4%B8%80%E4%B8%AA','xxxxxx',6,0,1,'cccc',15855558888,0,0,'	%E7%9C%81%E4','','','',0,'afsfsaf'),(4,'%E4%B8%80%E4%B8%AA','xxxxxx',6,0,0,'cccc',15855558888,0,0,'%E7%9C%81%E4%BB%BD','%E5%9C%B0%E7%BA%A7%E5%B8%82','%E5%B8%82%E3%80%81%E5%8E%BF%E7%BA%A7%E5%B8%82','',0,'2463ebe337d6050391720ba51c703c4d'),(5,'%E4%B8%80%E4%B8%AA','xxxxxx',6,0,0,'xxx',15888888888,5000,0,'%E7%9C%81%E4%BB%BD','%E5%9C%B0%E7%BA%A7%E5%B8%82','%E5%B8%82%E3%80%81%E5%8E%BF%E7%BA%A7%E5%B8%82','',0,'981a59c0342a24aae31ddb9397d93555'),(6,'%E4%B8%80%E4%B8%AA','xxxxxx',6,0,5,'xxxx',15988888888,0,0,'省份','地级市','市、县级市','',0,'615db57aa314529aaa0fbe95b3e95bd3'),(7,'%E4%B8%80%E4%B8%AA','xxxxxx',6,0,5,'xxxx',15988888888,0,0,'省份','地级市','市、县级市','',0,'615db57aa314529aaa0fbe95b3e95bd3'),(8,'%E4%B8%80%E4%B8%AA','xxxxxx',6,0,5,'xxxx',15888888888,0,0,'省份','地级市','市、县级市','',0,'615db57aa314529aaa0fbe95b3e95bd3'),(9,'%E4%B8%80%E4%B8%AA','xxxxxx',6,0,5,'xxxx',15888888888,0,0,'省份','地级市','市、县级市','',0,'615db57aa314529aaa0fbe95b3e95bd3'),(10,'%E4%B8%80%E4%B8%AA','xxxxxx',6,0,5,'aaa',15988888888,0,0,'省份','地级市','市、县级市','',0,'615db57aa314529aaa0fbe95b3e95bd3'),(11,'%E4%B8%80%E4%B8%AA','xxxxxx',6,0,5,'ssss',15788889999,0,0,'省份','地级市','市、县级市','',0,'615db57aa314529aaa0fbe95b3e95bd3');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-07 17:03:58

--20170115

INSERT INTO `menu` VALUES

(9,'公告编缉','/admin/notice',99,2,1);

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `type` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `content` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'大厅公告','2222222222222'),(2,'消息','bbbbbcccceeeee'),(3,'横幅','sssssddddd'),(4,'联系我们','xxxx'),(5,'公告','safsafsafdsadf');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-15 19:24:15