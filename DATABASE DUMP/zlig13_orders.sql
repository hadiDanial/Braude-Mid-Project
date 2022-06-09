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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderId` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `branchId` int NOT NULL,
  `orderStatus` varchar(32) DEFAULT NULL,
  `totalCost` float DEFAULT NULL,
  `greetingCard` varchar(256) DEFAULT NULL,
  `details` varchar(256) DEFAULT NULL,
  `orderDate` timestamp NULL DEFAULT NULL,
  `deliveryDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `userId` (`userId`),
  KEY `branchId` (`branchId`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`branchId`) REFERENCES `branches` (`branchId`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,123456789,1,'Accepted',15,'Hello','Valentines roses','2022-06-06 20:55:44','2022-06-06 21:25:44'),(2,123456789,1,'Delivered',15,'Greetings','bla bla','2022-06-06 20:55:44','2022-06-13 20:55:44'),(3,123456789,1,'ToBeDelivered',15,'DEAL','Royal','2022-06-06 20:55:44','2022-06-09 20:55:44'),(4,987654321,1,'ToBeDelivered',15,'Happy birthday!','Birthday flowers','2022-06-06 20:55:44','2022-06-11 20:55:44'),(5,987654321,1,'Accepted',15,'WEDDING!!!','Bridal Bouquet','2022-06-06 20:55:44','2022-07-06 20:55:44'),(6,123456789,1,'Accepted',1393,'dsaasdasdas',NULL,'2022-06-06 23:26:19','2022-06-21 15:26:00'),(7,123456789,2,'Pending',704,'sadfsadfsd afas af',NULL,'2022-06-06 23:29:49','2022-06-20 20:29:00'),(8,123456789,2,'ToBeDelivered',602,'pls work',NULL,'2022-06-06 23:30:43','2022-06-22 03:30:00'),(9,623456789,2,'Accepted',1647,'sadasdasdas','CartItem [catalogItem=Rose (Flower, Red), quantity=3] | CartItem [catalogItem=Product [productId=2, productName=Cactus, price=199.0], quantity=3] | CartItem [catalogItem=Product [productId=3, productName=Bridal Flowers, price=500.0], quantity=2] | ','2022-06-07 06:56:09','2022-06-21 06:55:00'),(10,623456789,2,'Pending',739,'fdasf','CartItem [catalogItem=Rose (Flower, Red), quantity=1] | CartItem [catalogItem=Product [productId=2, productName=Cactus, price=199.0], quantity=1] | CartItem [catalogItem=Product [productId=3, productName=Bridal Flowers, price=500.0], quantity=1] | ','2022-06-07 07:03:30','2022-06-29 08:03:00'),(11,623456789,2,'Pending',1240,'ewrfwfef','CartItem [catalogItem=Lemon Sapling (Seedling, None), quantity=2] | CartItem [catalogItem=Product [productId=3, productName=Bridal Flowers, price=500.0], quantity=2] | ','2022-06-07 08:19:32','2022-06-13 05:18:00'),(12,623456789,1,'Pending',40,'sdf','CartItem [catalogItem=Rose (Flower, Red), quantity=1] | ','2022-06-07 08:28:24','2022-06-29 06:27:00'),(13,623456789,2,'Pending',5,'dsadas','CartItem [catalogItem=Rose (Flower, Red), quantity=1] | ','2022-06-07 08:36:25','2022-06-29 06:36:00'),(14,623456789,2,'Pending',209,'rgf','CartItem [catalogItem=Rose (Flower, Red), quantity=2] | CartItem [catalogItem=Product [productId=2, productName=Cactus, price=199.0], quantity=1] | ','2022-06-07 10:32:15','2022-06-16 05:31:00'),(15,623456789,2,'Pending',699,'mhhvgm','CartItem [catalogItem=Product [productId=3, productName=Bridal Flowers, price=500.0], quantity=1] | CartItem [catalogItem=Product [productId=2, productName=Cactus, price=199.0], quantity=1] | ','2022-06-07 10:39:07','2022-06-23 06:49:00'),(16,623456789,2,'Pending',5,'ghfgh','CartItem [catalogItem=Rose (Flower, Red), quantity=1] | ','2022-06-07 10:56:54','2022-06-30 08:55:00'),(17,123456789,2,'Pending',45,'','CartItem [catalogItem=Rose (Flower, Red), quantity=2] | ','2022-06-07 10:59:15','2022-06-29 04:58:00'),(18,623456789,1,'Pending',408,'Hello!','CartItem [catalogItem=Rose (Flower, Red), quantity=2] | CartItem [catalogItem=Product [productId=2, productName=Cactus, price=199.0], quantity=2] | ','2022-06-08 12:40:50','2022-06-16 18:39:00'),(19,623456789,1,'Pending',10,'hello','CartItem [catalogItem=Rose (Flower, Red), quantity=2] | ','2022-06-09 14:28:00','2022-06-22 20:27:00'),(20,623456789,1,'Pending',234,'test','CartItem [catalogItem=Product [productId=2, productName=Cactus, price=199.0], quantity=1] | ','2022-06-09 14:29:44','2022-06-22 18:29:00'),(21,623456789,1,'Pending',234,'test','CartItem [catalogItem=Product [productId=2, productName=Cactus, price=199.0], quantity=1] | ','2022-06-09 14:29:44','2022-06-22 18:29:00'),(22,623456789,1,'Pending',209,'','CartItem [catalogItem=Rose (Flower, Red), quantity=2] | CartItem [catalogItem=Product [productId=2, productName=Cactus, price=199.0], quantity=1] | ','2022-06-09 16:11:34','2022-06-16 19:10:00'),(23,623456789,1,'Pending',433,'hadi	','CartItem [catalogItem=Product [productId=2, productName=Cactus, price=199.0], quantity=2] | ','2022-06-09 16:18:37','2022-06-29 20:17:00'),(24,123456789,2,'Pending',510,'FFF','CartItem [catalogItem=Rose (Flower, Red), quantity=2] | CartItem [catalogItem=Product [productId=3, productName=Bridal Flowers, price=500.0], quantity=1] | ','2022-06-09 16:26:35','2022-06-16 19:26:00'),(25,123456789,1,'Pending',699,'hagfsagsf','CartItem [catalogItem=Product [productId=3, productName=Bridal Flowers, price=500.0], quantity=1] | CartItem [catalogItem=Product [productId=2, productName=Cactus, price=199.0], quantity=1] | ','2022-06-09 16:34:20','2022-06-29 16:34:00');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-10  2:58:55
