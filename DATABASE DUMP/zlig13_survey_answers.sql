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
-- Table structure for table `survey_answers`
--

DROP TABLE IF EXISTS `survey_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey_answers` (
  `answerId` int NOT NULL AUTO_INCREMENT,
  `customerId` int NOT NULL,
  `orderId` int NOT NULL,
  `surveyId` int NOT NULL,
  `a1` tinyint NOT NULL,
  `a2` tinyint NOT NULL,
  `a3` tinyint NOT NULL,
  `a4` tinyint NOT NULL,
  `a5` tinyint NOT NULL,
  `a6` tinyint NOT NULL,
  PRIMARY KEY (`answerId`),
  KEY `surveyId` (`surveyId`),
  KEY `customerId` (`customerId`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `survey_answers_ibfk_1` FOREIGN KEY (`surveyId`) REFERENCES `surveys` (`surveyId`),
  CONSTRAINT `survey_answers_ibfk_2` FOREIGN KEY (`customerId`) REFERENCES `users` (`userId`),
  CONSTRAINT `survey_answers_ibfk_3` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`),
  CONSTRAINT `val_range` CHECK (((`a1` >= 1) and (`a1` <= 10) and (`a2` >= 1) and (`a2` <= 10) and (`a3` >= 1) and (`a3` <= 10) and (`a4` >= 1) and (`a4` <= 10) and (`a5` >= 1) and (`a5` <= 10) and (`a6` >= 1) and (`a6` <= 10)))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_answers`
--

LOCK TABLES `survey_answers` WRITE;
/*!40000 ALTER TABLE `survey_answers` DISABLE KEYS */;
INSERT INTO `survey_answers` VALUES (1,1,1,1,1,2,3,4,5,10),(2,1,1,2,5,4,8,1,1,1),(3,1,1,3,6,5,3,4,9,7);
/*!40000 ALTER TABLE `survey_answers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-10  2:58:57
