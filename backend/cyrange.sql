-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cyrange
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES ('0','全部题目',21),('1','信息搜集',2),('2','sql注入',7),('3','反序列化',8),('4','命令执行',4),('5','文件上传',6);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cyrangechallenges`
--

DROP TABLE IF EXISTS `cyrangechallenges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cyrangechallenges` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `author` varchar(255) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(255) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  `deployment_fileurl` varchar(255) DEFAULT NULL,
  `service_fileurl` varchar(255) DEFAULT NULL,
  `ingress_fileurl` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `points` int DEFAULT NULL,
  `participants` int DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cyrangechallenges`
--

LOCK TABLES `cyrangechallenges` WRITE;
/*!40000 ALTER TABLE `cyrangechallenges` DISABLE KEYS */;
INSERT INTO `cyrangechallenges` VALUES (1,'信息搜集第一题\n提示:代码注释闯大祸','1','1','2025-01-20 11:16:55','2025-02-23 07:59:38','1','1','https://web-6666666.oss-cn-beijing.aliyuncs.com/1deployment.yaml','\nhttps://web-6666666.oss-cn-beijing.aliyuncs.com/1service.yaml','https://web-6666666.oss-cn-beijing.aliyuncs.com/1ingress.yaml','web-1',10,5),(2,'sql注入第一题 提示:简单sql注入','2','1','2025-01-29 04:09:14','2025-02-20 13:26:46','1','1','https://web-6666666.oss-cn-beijing.aliyuncs.com/1deployment.yaml','\nhttps://web-6666666.oss-cn-beijing.aliyuncs.com/1service.yaml','https://web-6666666.oss-cn-beijing.aliyuncs.com/1ingress.yaml','web-2',10,5),(3,'sql注入第二题 提示:空格被过滤了哦','2','2','2025-02-02 11:06:09','2025-02-20 13:24:20','2','2','https://web-6666666.oss-cn-beijing.aliyuncs.com/1deployment.yaml','https://web-6666666.oss-cn-beijing.aliyuncs.com/1service.yaml',NULL,'web-3',10,4),(4,'信息搜集第二题 抓到10000分','1','1','2025-02-04 13:01:26','2025-02-20 11:41:22','1','1','https://web-6666666.oss-cn-beijing.aliyuncs.com/1deployment.yaml','https://web-6666666.oss-cn-beijing.aliyuncs.com/1service.yaml',NULL,'web-4',10,4),(5,'反序列化第一题 ','3','2','2025-02-06 03:32:08','2025-02-06 08:28:27','2','2',NULL,NULL,NULL,'web-5',10,4),(6,'反序列化第二题 ','3','2','2025-02-06 08:05:52','2025-02-20 11:49:00','2','2',NULL,NULL,NULL,'web-6',10,4),(7,'反序列化第三题 ','3','2','2025-02-06 08:06:35','2025-02-07 13:36:00','2','2',NULL,NULL,NULL,'web-7',10,4),(8,'反序列化第四题 ','3','2','2025-02-07 13:34:36','2025-02-20 11:50:55','2','2',NULL,NULL,NULL,'web-8',10,5),(9,'反序列化第五题 ','3','2','2025-02-07 13:34:34','2025-02-20 11:51:35','2','2',NULL,NULL,NULL,'web-9',10,5),(10,'反序列化第六题 ','3','2','2025-02-07 13:34:38','2025-02-07 13:34:39','2','2',NULL,NULL,NULL,'web-10',10,4),(11,'反序列化第七题','3','2','2025-02-08 11:20:49','2025-02-08 11:20:53','2','2',NULL,NULL,NULL,'web-11',10,5),(12,'反序列化第八题','3','2','2025-02-08 11:20:50','2025-02-08 11:20:52','2','2',NULL,NULL,NULL,'web-12',10,5),(13,'sql注入第三题 提示:数字型','2','2','2025-02-13 11:15:49','2025-02-13 11:15:51','2','2',NULL,NULL,NULL,'web-13',10,5),(14,'sql注入第四题 提示:数字型','2','2','2025-02-13 11:15:47','2025-02-13 11:15:50','2','2',NULL,NULL,NULL,'web-14',10,5),(15,'sql注入第五题 提示:数字型','2','2','2025-02-13 11:15:48','2025-02-13 11:15:52','2','2',NULL,NULL,NULL,'web-15',10,5),(16,'sql注入第六题 提示:二次注入','2','2','2025-02-15 13:12:37','2025-02-15 13:12:38','2','2',NULL,NULL,NULL,'web-16',10,5),(17,'sql注入第六题 提示:盲注','2','2','2025-02-17 12:29:17','2025-02-17 12:32:00','2','2',NULL,NULL,NULL,'web-17',10,5),(18,'命令执行第一题','4','1','2025-02-17 12:31:58','2025-02-20 11:42:25','1','1',NULL,NULL,NULL,'web-18',10,7),(19,'命令执行第二题','4','1','2025-02-17 12:32:06','2025-02-20 11:45:22','1','1',NULL,NULL,NULL,'web-19',10,7),(20,'命令执行第三题','4','1','2025-02-17 12:32:07','2025-02-20 11:46:19','1','1',NULL,NULL,NULL,'web-20',10,6),(21,'命令执行第四题','4','1','2025-02-17 12:32:08','2025-02-17 12:32:05','1','1',NULL,NULL,NULL,'web-21',10,5),(22,'sql注入第七题 提示:盲注','2','2','2025-02-18 14:09:04','2025-02-18 14:09:07','2','2',NULL,NULL,NULL,'web-22',10,5),(23,'sql注入第八题 提示:报错注入','2','2','2025-02-18 14:09:09','2025-02-18 14:09:06','2','2',NULL,NULL,NULL,'web-23',10,5),(24,'文件上传 第一题','5','2','2025-02-24 12:32:37','2025-02-24 13:03:44','2','2',NULL,NULL,NULL,'web-24',10,6),(25,'文件上传 第二题','5','2','2025-02-24 12:34:09','2025-02-24 13:03:52','2','2',NULL,NULL,NULL,'web-25',10,5),(26,'文件上传 第三题','5','2','2025-02-24 12:34:11','2025-02-24 13:04:00','2','2',NULL,NULL,NULL,'web-26',10,5),(27,'文件上传 第四题','5','2','2025-02-24 12:34:14','2025-02-24 13:04:05','2','2',NULL,NULL,NULL,'web-27',10,5),(28,'文件上传 第五题','5','2','2025-02-24 12:34:15','2025-02-24 13:04:11','2','2',NULL,NULL,NULL,'web-28',10,5),(29,'文件上传 第六题','5','2','2025-02-24 12:34:00','2025-02-24 13:04:20','2','2',NULL,NULL,NULL,'web-29',10,5),(30,'文件上传 第七题','5','2','2025-02-25 14:02:24','2025-02-25 14:02:52','2','2',NULL,NULL,NULL,'web-30',10,5),(32,'文件上传','5','2','2025-02-26 13:23:41','2025-03-23 12:00:53','2','2',NULL,NULL,NULL,'web-31',10,5);
/*!40000 ALTER TABLE `cyrangechallenges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `total_score` int DEFAULT '0',
  `mail` varchar(25) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','e10adc3949ba59abbe56e057f20f883e',40,'123456789@qq.com','12345678912'),(2,'user','e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL),(3,'user1','e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL),(4,'user2','e10adc3949ba59abbe56e057f20f883e',60,'123456789@qq.com','12345678912'),(6,'user3','e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL),(7,'13310214893','e10adc3949ba59abbe56e057f20f883e',110,'1220452704@qq.com','13310214893'),(8,'123456789','e10adc3949ba59abbe56e057f20f883e',30,'123456789@qq.com','12345678912'),(9,'123456','e10adc3949ba59abbe56e057f20f883e',10,NULL,NULL),(10,'13378383662','e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_challenge_record`
--

DROP TABLE IF EXISTS `user_challenge_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_challenge_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户 ID',
  `challenge_id` bigint NOT NULL COMMENT '题目 ID',
  `is_correct` tinyint NOT NULL COMMENT '是否答对（1: 是, 0: 否）',
  `finish_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_challenge_record`
--

LOCK TABLES `user_challenge_record` WRITE;
/*!40000 ALTER TABLE `user_challenge_record` DISABLE KEYS */;
INSERT INTO `user_challenge_record` VALUES (1,7,1,1,'2025-02-19 13:56:26'),(2,7,5,1,'2024-02-19 13:56:31'),(3,1,3,1,'2025-01-19 12:32:47'),(4,1,4,1,'2025-02-19 12:32:53'),(5,1,18,1,'2024-11-24 12:33:00'),(6,1,1,1,'2024-12-24 12:33:11'),(7,4,1,1,'2024-06-19 13:56:34'),(8,4,18,1,'2024-11-19 13:56:40'),(9,4,19,1,'2025-01-19 13:56:50'),(10,4,3,1,'2025-02-19 13:56:46'),(11,7,2,1,'2025-02-20 11:39:03'),(12,7,3,1,'2025-02-20 11:40:16'),(13,7,4,1,'2025-02-20 11:41:22'),(14,7,18,1,'2025-02-20 11:42:25'),(15,7,19,1,'2025-02-20 11:45:22'),(16,7,20,1,'2025-02-20 11:46:19'),(17,7,6,1,'2025-02-20 11:49:00'),(18,7,8,1,'2025-02-20 11:50:55'),(19,7,9,1,'2025-02-20 11:51:35'),(20,8,1,1,'2025-02-20 13:00:56'),(21,8,2,1,'2025-02-20 13:23:15'),(22,8,3,1,'2025-02-20 13:24:20'),(23,4,2,1,'2025-02-20 13:26:46'),(24,9,1,1,'2025-02-23 07:59:38'),(25,4,25,1,'2025-02-24 12:51:53');
/*!40000 ALTER TABLE `user_challenge_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-19 15:38:51
