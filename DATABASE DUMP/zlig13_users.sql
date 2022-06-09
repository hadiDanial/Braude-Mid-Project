-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zlig13
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userId` int NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `emailAddress` varchar(32) NOT NULL,
  `phoneNumber` varchar(20) NOT NULL,
  `role` varchar(30) NOT NULL,
  `status` varchar(20) NOT NULL,
  `credit` float DEFAULT '0',
  `isLoggedIn` tinyint(1) DEFAULT NULL,
  `lastLoginDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'amr','123','Amr','Kalany','AmrKal@gmail.com','0504707027','CEO','Active',0,0,'2022-06-09 17:41:54'),(2,'amre','123','Amre','Kalanye','AmrKale@gmail.com','0504707028','ChainEmployee','Active',0,0,'2022-06-07 06:34:45'),(25456486,'delivery','123','hadi','dam','ads@fas.com','0526600419','DeliveryPerson','Pending',0,0,'2022-06-09 12:30:23'),(123421324,'gsgsd','32rwef','re','fds','gfsg@ggg.com','1241411241','Customer','Active',0,0,NULL),(123456789,'Hadi','123','Hadi','Danial','hadi@gmail.com','05223113','Customer','Active',199,0,'2022-06-10 00:29:12'),(208864751,'faaaaaaaaaaaaak','ffffff123','hello','itsme','faku@haha.com','0526600419','Customer','Pending',0,0,'2022-06-07 06:37:29'),(223456789,'cse','123','Hadi','Danial','hadi@gmail.com','05223113','CustomerServiceEmployee','Active',0,0,'2022-06-07 05:18:21'),(323456789,'ceo','123','Hadi','Danial','hadi@gmail.com','05223113','CEO','Active',0,0,'2022-06-08 12:43:01'),(423456789,'manager','123','Hadi','Danial','hadi@gmail.com','05223113','BranchManager','Active',0,0,'2022-06-10 02:25:09'),(623456789,'customer','123','Hadi','Danial','hadi@gmail.com','05223113','Customer','Active',95,0,'2022-06-10 02:25:24'),(987654321,'Yosef','bestpassword456','Yosef','Awad','yosef@gmail.com','052231132','BranchManager','Active',0,NULL,NULL);
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

-- Dump completed on 2022-06-10  2:58:56
