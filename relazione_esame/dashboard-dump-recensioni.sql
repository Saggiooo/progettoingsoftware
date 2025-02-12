CREATE DATABASE  IF NOT EXISTS `dashboard` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dashboard`;
-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: dashboard
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `event_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `general_rating` int DEFAULT NULL,
  `location_rating` int DEFAULT NULL,
  `price_rating` int DEFAULT NULL,
  `staff_rating` int DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`review_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (13,1,123,'Ottimo film','bellisima la sala e tutto il contesto',5,3,3,3,'2024-11-12 00:00:00.000000'),(14,1,123,'Ciao','non bellissimo',5,NULL,NULL,NULL,'2024-11-12 00:00:00.000000'),(15,1,123,'Non ci siamo','aubaledi',4,NULL,NULL,NULL,'2024-11-12 00:00:00.000000'),(16,1,123,'Ottimo evento','bravi!',4,NULL,NULL,NULL,'2024-11-12 00:00:00.000000'),(17,1,123,'Molto bravi','veramente bello',3,4,5,5,'2024-11-12 00:00:00.000000'),(18,1,1,'Complimenti','superlativo',1,2,3,4,'2023-12-12 00:00:00.000000'),(19,1,123,'Mi aspettavo meglio','Luogo non adatto. staff molto cortese',3,2,3,4,'2024-11-12 00:00:00.000000'),(20,1,123,'male male','mai e poi mai lo consiglierei',1,1,1,1,'2024-11-12 11:33:10.692046'),(21,1,123,'Grazie di tutto','wow',5,NULL,NULL,NULL,'2024-11-13 12:07:19.061642'),(24,5,125,'L\'evento dell\'anno','bravissimi',4,NULL,NULL,NULL,'2025-01-09 16:25:20.236185'),(26,5,125,'Atroce','Atroce e basta',3,NULL,NULL,NULL,'2025-01-09 16:42:43.665673'),(27,5,125,'Complimentoni','molto bravi tutti',3,NULL,NULL,NULL,'2025-01-09 16:44:57.043035'),(28,9,125,'veramente molto preparati','continuate così, consigliato',2,NULL,NULL,NULL,'2025-01-28 19:32:10.954675'),(29,1,125,'Complimenti all\'organizzatare','Ho amato questo evento e lo consiglio',5,NULL,NULL,NULL,'2025-02-11 16:01:41.415513'),(30,1,125,'Quasi perfetto','Tutto molto bello ma l\'uscita della gente doveva essere organizzata diversamente',4,NULL,NULL,NULL,'2025-02-11 16:02:11.586761'),(31,1,125,'Non ci siamo','Evitate di prenotare tramite questa organizzazione, non sono capaci...',1,NULL,NULL,NULL,'2025-02-11 16:02:33.677233'),(35,1,125,'Ottimo evento!','grazie!',5,NULL,NULL,NULL,'2025-02-12 15:18:24.744346');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-12 19:54:52
